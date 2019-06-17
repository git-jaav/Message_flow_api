package pe.jaav.sistemas.messageFlowApi.schedule;

import java.util.List;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.jaav.common.util.UtilesCommons;
import pe.jaav.common.util.model.ResultTx;
import pe.jaav.sistemas.messageFlow.service.GenMensajeAccionService;
import pe.jaav.sistemas.messageFlowApi.model.general.GenMensajeAccionContextJson;
import pe.jaav.sistemas.messageFlowApi.model.general.GenMensajeAccionJson;
import pe.jaav.sistemas.messageFlowApi.model.general.SysUsuarioJson;
import pe.jaav.sistemas.messageFlowApi.util.UtilesRest;
import pe.jaav.sistemas.utiles.AsyncUtiles;
import pe.jaav.sistemas.utiles.Constant;

@Service
public class ProcesoSendEmailRunnable extends GeneralRunnable{

	
	/**Log*/
	private static final Logger logger = LoggerFactory.getLogger(ProcesoSendEmailRunnable.class);		
	
	@Autowired
	GenMensajeAccionService genMensajeAccionService;
	
	/**
	 * Default, iniciar como Spring component
	 */
	public ProcesoSendEmailRunnable() {
		super();	
	}

	/** Para test con un mensaje en particular
	 * @param mensajeTest
	 */
	public ProcesoSendEmailRunnable(long timeMilliSecond) {
		super(timeMilliSecond);		
	}

	/** para determinar el DELAY en pruebas
	 * @param timeMilliSecond
	 */
	public ProcesoSendEmailRunnable(String mensajeTest) {
		super(mensajeTest);		
	}

	@Override
	public void run() {
		try {
			countCicloActual++;
			//ejecutarModoTest();
			ejecutarProceso();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	/**
	 * Proceos de recibir los pagos realizados no procesados 
	 * (Y procesar su matricula, si hubiere, o actualizar los montos )
	 */
	public void ejecutarProceso() {
		//TIPO_PROCESO_OBL_COBRAR
		try {
			String uriBase = UtilesRest.getParamUrlBaseiAPIConsulta();
			
			/**Comenzar Validacion*/
			boolean valido = false;
			String token = getComponentController().getTokenSecurity();
			Integer idSecurity = getComponentController().getIdSecurity();
			if(UtilesCommons.noEsVacio(token)
				&& UtilesCommons.noEsVacio(idSecurity)) {
				
				valido =  genMensajeAccionService.validarToken(uriBase, getComponentController().getTokenSecurity(),
						getComponentController().getIdSecurity());				
				
			}
			
			if(!valido) {	
				/*Si no es valido obtener la autorizacion del usuario (Token actual)*/
				ResultTx<SysUsuarioJson>  usuarioAuth =  genMensajeAccionService.autorizarUsuarioEnv(uriBase);
				if(usuarioAuth.isOk()) {
					token =usuarioAuth.getResult().getTokenSecurity();
					idSecurity =usuarioAuth.getResult().getUsuaId();
					//Actualizar controller Schedulle
					getComponentController().setTokenSecurity(token);
					getComponentController().setIdSecurity(idSecurity);
				}
			}
			
			/**Comenzar Proceso*/
			if(UtilesCommons.noEsVacio(token)
					&& UtilesCommons.noEsVacio(idSecurity)) {
							
				List<GenMensajeAccionContextJson>  listaContext = genMensajeAccionService.listarPendientes(uriBase,token);
				if(UtilesCommons.noEsVacio(listaContext)) {
					
					for(GenMensajeAccionContextJson msgContext :  listaContext) {
						GenMensajeAccionJson objUpd =  msgContext.getObjMensajeAccion();
						objUpd.setMsgEstadoAccion(Constant.MSG_ESTADO_EN_ESPERA);
						int result =   genMensajeAccionService.actualizar(uriBase, objUpd,token);
						if(result > 0) {
							
							/** ENVIAR CORREO Asincronamente - OL */
							Consumer<GenMensajeAccionContextJson> accionEventoEnvioCorreo = (p -> {iniciarEnvioCorreo(p); });
							AsyncUtiles.ejecutarAccionAsync(accionEventoEnvioCorreo, msgContext);
							
							/** ENVIAR CORREO sync - OL */
							//iniciarEnvioCorreo(msgContext);	
						}					
					}					
					String result = getMensajeCicloProceso()+"::[PROCESO_SEND_EMAIL]::"+"[INFO]::"+"[Msg]::"+"Por Procesar: "+""+listaContext.size()+" elements.";
					logger.info(result);
				}else {
					String result = getMensajeCicloProceso()+"::[PROCESO_SEND_EMAIL]::"+"[WARN]::"+"[Msg]::"+"Sin data que procesar"+"";
					logger.warn(result);
				}				
			}

			//ejecutarModoTest();			
		}catch(Exception e) {
			e.printStackTrace();
			String result = getMensajeCicloProceso()+"::[PROCESO_SEND_EMAIL]::"+"[ERROR]::"+"[Exception]::"+e.getMessage()+"";
			System.out.println(result);
			logger.error(result);
		}

	}	
		
	
	/**
	 * Iniciar el envio de Correo Asyncrono
	 * 
	 * @param indicaTipo
	 * @param mensajeBodyAux
	 */
	public void iniciarEnvioCorreo(GenMensajeAccionContextJson contextMsg) {
		/** */
		/*
		 */
		String uriBase = UtilesRest. getParamUrlBaseiAPIConsulta();
		ResultTx<GenMensajeAccionContextJson>  result =  genMensajeAccionService.enviarCorreo(contextMsg);
		if(result != null && result.isOk()) {			
			contextMsg.getObjMensajeAccion().setMsgEstadoAccion(Constant.MSG_ESTADO_FINALIZADO);
			genMensajeAccionService.actualizar(uriBase, contextMsg.getObjMensajeAccion(),
					getComponentController().getTokenSecurity());
		}else {
			contextMsg.getObjMensajeAccion().setMsgEstadoAccion(Constant.MSG_ESTADO_PENDIENTE);
			genMensajeAccionService.actualizar(uriBase, contextMsg.getObjMensajeAccion(),
					getComponentController().getTokenSecurity());
		}
	}		
	
}

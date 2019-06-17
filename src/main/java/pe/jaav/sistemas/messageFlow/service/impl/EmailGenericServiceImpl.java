package pe.jaav.sistemas.messageFlow.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import pe.jaav.common.jmail.model.Email;
import pe.jaav.common.jmail.service.EmailService;
import pe.jaav.common.util.UtilesCommons;
import pe.jaav.sistemas.messageFlow.service.EmailGenericService;
import pe.jaav.sistemas.messageFlowApi.util.UtilesRest;
import pe.jaav.sistemas.utiles.Constant;


@Service("emailGenericService")
public class EmailGenericServiceImpl implements EmailGenericService{


	/* (non-Javadoc)
	 * @see pe.jaav.sistemas.seguridadgeneral.service.EmailGenericService#enviarCorreo(pe.jaav.common.jmail.model.Email)
	 */
	@Override
	public int enviarCorreo(Email objCorreo) {
		int resultMsg = 0;
		try{
			/**1: BUSCAR PARAMETROS DE CONEX*/		
			boolean halladoParametrosConex = false;
			/**2: SET PAR|METROS DE CONEXI|N**/
			if(objCorreo!=null){
				if(!objCorreo.isParametrosConexionSet()){
					
					String PAR_VAL_CORREO_ENVIA = UtilesRest.getVariableEntornoSystemServer(Constant.ENV_VARIABLE_PAR_CORREO_ENVIA);
					String PAR_VAL_CLAVE_CORREO_ENVIA = UtilesRest.getVariableEntornoSystemServer(Constant.ENV_VARIABLE_PAR_CLAVE_CORREO_ENVIA);					
					String PAR_VAL_CORREO_HOST = UtilesRest.getVariableEntornoSystemServer(Constant.ENV_VARIABLE_PAR_CORREO_HOST);
					String PAR_VAL_CORREO_PORT = UtilesRest.getVariableEntornoSystemServer(Constant.ENV_VARIABLE_PAR_CORREO_PORT);
					String PAR_VAL_CORREO_SPORT = UtilesRest.getVariableEntornoSystemServer(Constant.ENV_VARIABLE_PAR_CORREO_SPORT);
					String PAR_VAL_CORREO_TTL = UtilesRest.getVariableEntornoSystemServer(Constant.ENV_VARIABLE_PAR_CORREO_TTL);
					String PAR_VAL_CORREO_ACCOUNT = UtilesRest.getVariableEntornoSystemServer(Constant.ENV_VARIABLE_PAR_CORREO_ACCOUNT);					
					
					if(UtilesCommons.noEsVacio(PAR_VAL_CORREO_ENVIA)
						&& UtilesCommons.noEsVacio(PAR_VAL_CLAVE_CORREO_ENVIA)
						&& UtilesCommons.noEsVacio(PAR_VAL_CORREO_HOST)
						&& UtilesCommons.noEsVacio(PAR_VAL_CORREO_PORT)
						&& UtilesCommons.noEsVacio(PAR_VAL_CORREO_ACCOUNT)
						) {
						halladoParametrosConex = true;	
					}
					
					if(halladoParametrosConex) {
						/**
						 * Orden de Paramentros
						 * 01:PAR_VAL_CORREO_ENVIA
						 * 02:PAR_VAL_CLAVE_CORREO_ENVIA
						 * 03:PAR_VAL_CORREO_HOST
						 * 04:PAR_VAL_CORREO_PORT
						 * 05:PAR_VAL_CORREO_SPORT
						 * 06:PAR_VAL_CORREO_TTL
						 * 07:PAR_VAL_CORREO_ACCOUNT
						 ***/
						EmailService.setEmailParametrosConexion(objCorreo, 
								PAR_VAL_CORREO_ENVIA, 
								PAR_VAL_CLAVE_CORREO_ENVIA,  
								PAR_VAL_CORREO_HOST,  
								PAR_VAL_CORREO_PORT, 
								PAR_VAL_CORREO_SPORT,  
								PAR_VAL_CORREO_TTL,
								PAR_VAL_CORREO_ACCOUNT );
						
						/**3: ENVIO CORREOS */
						resultMsg = EmailService.enviarEmail(objCorreo,objCorreo.isEsBodyPlantilla());							
						//resultMsg = EmailService.enviarEmailAWS(objCorreo,objCorreo.isEsBodyPlantilla());								
					}
						
				}

			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return resultMsg;
	}

//	@Override
//	public int enviarCorreo(Email objCorreo, String procesoPlantilla) {
//		int resultMsg = 0;
//		try{
//			if(objCorreo!=null){
//				/**1: BUSCAR PLANTILLAS PARA EL BODY*/		
//				// BUSCAR PARAMETROS DE CONEX en DB
//				boolean halladoPlantillaCorreo = false;
//				String plantillaCorreo = null;
//				if(UtilesCommons.noEsVacio(procesoPlantilla)){
//					GenTablasVariosDetalle objMiscPlant = new GenTablasVariosDetalle (); 
//					objMiscPlant.setAplicacionCodigo(Utiles.getPropertyService("PAR_CODE_APP"));
//					objMiscPlant.setCompania(Utiles.getPropertyService("PAR_CODE_COMPANY"));			
//					objMiscPlant.setCodigoTabla(Utiles.getPropertyService("PAR_CODIGOTABLA_PLANT_CORREOS"));
//					objMiscPlant.setEstado(Constant.ACTIVO);
//					objMiscPlant.setValorCodigo1(procesoPlantilla);
//					List<MaMiscelaneosDetalle>  listaPt = maMiscelaneosDetalleService.listarElementos(objMiscPlant,true);
//					if(listaPt!=null){
//						if(listaPt.size()>0){
//							halladoPlantillaCorreo = true;
//							plantillaCorreo = listaPt.get(0).getDataTexto();
//						}
//					}
//				}
//
//				/**2: MATCH VARIABLES CON PLANTILLA CORREO**/
//				if(halladoPlantillaCorreo && plantillaCorreo!=null && objCorreo.isEsBodyPlantilla()){
//					objCorreo.setBody(plantillaCorreo);					
//				}else{
//					objCorreo.setEsBodyPlantilla(false);
//				}										
//			/**3: SET PAR|METROS DE CONEXI|N**/
//			
//				if(!objCorreo.isParametrosConexionSet()){
//					// BUSCAR PARAMETROS DE CONEX en DB
//					MaMiscelaneosDetalle objMisc = new MaMiscelaneosDetalle (); 
//					objMisc.setAplicacionCodigo(Utiles.getPropertyService("PAR_CODE_APP"));
//					objMisc.setCompania(Utiles.getPropertyService("PAR_CODE_COMPANY"));
//					objMisc.setCodigoTabla(Utiles.getPropertyService("PAR_CODIGOTABLA_VARCORREOS"));
//					objMisc.setEstado(Constant.ACTIVO);
//					boolean halladoParametrosConex = false;
//					List<MaMiscelaneosDetalle>  lista = maMiscelaneosDetalleService.listarElementos(objMisc,true);
//					if(lista!=null){
//						if(lista.size()>0){
//							halladoParametrosConex = true;
//							EmailService.setEmailParametrosConexion(objCorreo, 
//									lista.get(0).getValorCodigo1(), 
//									lista.get(0).getValorCodigo2(),  
//									lista.get(0).getValorCodigo3(),  
//									lista.get(0).getValorCodigo4(), 
//									lista.get(0).getValorCodigo5(),  
//									lista.get(0).getValorCodigo6(),
//									lista.get(0).getValorCodigo7()  );
//						}
//					}
//					if(!halladoParametrosConex){
//						EmailService.setEmailParametrosConexion(objCorreo, 
//								Utiles.getPropertyService("PAR_VAL_CORREO_ENVIA"), 
//								Utiles.getPropertyService("PAR_VAL_CLAVE_CORREO_ENVIA"),  
//								Utiles.getPropertyService("PAR_VAL_CORREO_HOST"),  
//								Utiles.getPropertyService("PAR_VAL_CORREO_PORT"), 
//								Utiles.getPropertyService("PAR_VAL_CORREO_SPORT"),  
//								Utiles.getPropertyService("PAR_VAL_CORREO_TTL"), 
//								Utiles.getPropertyService("PAR_VAL_CORREO_ACCOUNT"));
//					}					
//				}
//
//			}								
//			/**3: ENVIO CORREOS */
//			resultMsg = EmailService.enviarEmail(objCorreo,objCorreo.isEsBodyPlantilla());
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
//		return resultMsg;
//	}

	@Override
	public Email getObjetoMail(List<String> listCorreoDestinos, String subject, String body, String textBody,
			List<String> listPathFileAtached, Date correoFecha,boolean contienePlantilla) {
		Email email = 
				EmailService.construirMail(listCorreoDestinos, subject, body, 
						listPathFileAtached, correoFecha);
		email.setTextBody(textBody); 
		email.setEsBodyPlantilla(contienePlantilla);
		return email;
	}

	@Override
	public Email getObjetoMail(List<String> listCorreoDestinos, String subject, String bodyPlantilla,
			List<String> listPathFileAtached, Date correoFecha, Map<String, String> mapvariablesValores) {
		
		Email email = 
				EmailService.construirMail(listCorreoDestinos, subject, bodyPlantilla, 
						listPathFileAtached, correoFecha);
		email.setMapvariablesValores(mapvariablesValores);			
		if(mapvariablesValores!=null){
			email.setEsBodyPlantilla(true);	
		}		
		return email;
	}

	@Override
	public int enviarCorreoMixto(List<Email> listaObjCorreo) {
		// TODO Auto-generated method stub
		return 0;
	}



}

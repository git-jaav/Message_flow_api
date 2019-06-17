package pe.jaav.sistemas.messageFlow.service;

import java.util.List;

import pe.jaav.common.util.model.ResultTx;
import pe.jaav.sistemas.messageFlowApi.model.general.GenMensajeAccionContextJson;
import pe.jaav.sistemas.messageFlowApi.model.general.GenMensajeAccionJson;
import pe.jaav.sistemas.messageFlowApi.model.general.SysUsuarioJson;

public interface GenMensajeAccionService {

	public static final String URI_MSG_ACCION_REGISTRO_ACTUALIZAR = "/mensajeaccion/registro/actualizar";
	public static final String URI_MSG_ACCION_LISTAR_PENDIENTES = "/mensajeaccion/listar/pendientes";
	
	public static final String URI_AUTH_AUTORIZAR= "/auth/autorizar";
	public static final String URI_AUTH_BUSCAR_USUARIO_ID= "/usuario/auth//buscar/";
	
		
	public List<GenMensajeAccionContextJson> listarPendientes( String uriBase,String token);	
	public int actualizar( String uriBase,GenMensajeAccionJson objTransac,String token);	
	public ResultTx<GenMensajeAccionContextJson> enviarCorreo(GenMensajeAccionContextJson objTransac);
	public ResultTx<SysUsuarioJson> autorizarUsuarioEnv(String uriBase);
	
	public boolean validarToken( String uriBase,String token, Integer id);
	
	
}

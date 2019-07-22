package pe.jaav.sistemas.messageFlow.service.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import pe.jaav.common.util.UtilesCommons;
import pe.jaav.common.util.model.ResultTx;
import pe.jaav.sistemas.messageFlow.service.EmailGenericService;
import pe.jaav.sistemas.messageFlow.service.GenMensajeAccionService;
import pe.jaav.sistemas.messageFlowApi.model.general.GenMensajeAccionContextJson;
import pe.jaav.sistemas.messageFlowApi.model.general.GenMensajeAccionDestinoJson;
import pe.jaav.sistemas.messageFlowApi.model.general.GenMensajeAccionJson;
import pe.jaav.sistemas.messageFlowApi.model.general.SysUsuarioJson;
import pe.jaav.sistemas.messageFlowApi.security.UserCredentials;
import pe.jaav.sistemas.messageFlowApi.util.UtilesRest;
import pe.jaav.sistemas.utiles.Constant;

@Service("genMensajeAccionService")
public class GenMensajeAccionServiceImpl  implements GenMensajeAccionService {

	
	private static final Logger logger = LoggerFactory.getLogger(GenMensajeAccionServiceImpl.class);
	
	@Autowired
	EmailGenericService emailGenericService;
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<GenMensajeAccionContextJson>listarPendientes( String uriBase,String tokenSecurity) {
		try {
			if(UtilesCommons.noEsVacio(tokenSecurity)) {
				RestTemplate restTemplate = new RestTemplate();
				String uri =uriBase + URI_MSG_ACCION_LISTAR_PENDIENTES;		
				HttpHeaders headers = new HttpHeaders();		
				headers.set("content-type", "application/json;charset=UTF-8");   	    
			    headers.set(Constant.JWT_TOKEN_HEADER_PARAM, Constant.JWT_TOKEN_PREFIX+" "+tokenSecurity );    
				HttpEntity<?> request = new HttpEntity<>(headers);
				
				ResponseEntity<List<GenMensajeAccionContextJson>> result =
				        restTemplate.exchange(uri,HttpMethod.POST, request, 
				                    new ParameterizedTypeReference<List<GenMensajeAccionContextJson>>() {
				            });
				
				//ResponseEntity<List> result = restTemplate.postForEntity(uri, request, List.class);
			    //Verify request succeed
				Integer status = result.getStatusCodeValue();
				if(status == HttpStatus.ACCEPTED.value()
					|| status == HttpStatus.CREATED.value()
					|| status == HttpStatus.OK.value()) {
					//List<GenMensajeAccionContextJson> listaResult = UtilesCommons.getNewList();										
					List<GenMensajeAccionContextJson> listaResult =   (List<GenMensajeAccionContextJson>)result.getBody();
					if(UtilesCommons.noEsVacio(listaResult)) {		
						
//						for(Object objMsgAccion : listaResultAux) {
//							if(objMsgAccion instanceof GenMensajeAccionContextJson) {
//								listaResult.add((GenMensajeAccionContextJson) objMsgAccion);
//							}else if(objMsgAccion instanceof java.util.LinkedHashMap) {
//								LinkedHashMap<String, Object> lMap = (LinkedHashMap<String, Object>) objMsgAccion;
//								GenMensajeAccionContextJson objContext = new GenMensajeAccionContextJson();
//								Object objMsj = lMap.get("objMensajeAccion");
//								if(objMsj instanceof GenMensajeAccionJson) {
//									objContext.setObjMensajeAccion((GenMensajeAccionJson)objMsj);	
//								}								
//								Object objMsjDestino = lMap.get("listMensajeAccionDestino");
//								if(objMsjDestino instanceof List) {
//									objContext.setListMensajeAccionDestino((List<GenMensajeAccionDestinoJson>) objMsjDestino);
//								}
//								listaResult.add(objContext);
//							} 
//							//GenMensajeAccionJson msj =  objMsgAccion.getObjMensajeAccion();
//						}
						
				    	return listaResult;
				    }
				}else  if(status == HttpStatus.FORBIDDEN.value()
					|| status == HttpStatus.UNAUTHORIZED.value()) {
					return null;
					//ResultTx<SysUsuarioJson > usuarioResult = autorizarUsuarioEnv(uriBase);
					//if(usuarioResult.isOk()) {
						//String token = usuarioResult.getResult().getTokenSecurity();	
					//}					
				}				
			}
	
		}catch(HttpClientErrorException e) {
			e.printStackTrace();
		}				  
		return null;
	}

	@Override
	public int actualizar( String uriBase,GenMensajeAccionJson objTransac,String tokenSecurity) {
		try {
			if(UtilesCommons.noEsVacio(tokenSecurity)) {
				RestTemplate restTemplate = new RestTemplate();
				String uri =uriBase + URI_MSG_ACCION_REGISTRO_ACTUALIZAR;		
				HttpHeaders headers = new HttpHeaders();		
				headers.set("content-type", "application/json;charset=UTF-8");   	    
			    headers.set(Constant.JWT_TOKEN_HEADER_PARAM, Constant.JWT_TOKEN_PREFIX+" "+tokenSecurity );    
				HttpEntity<GenMensajeAccionJson> request = new HttpEntity<GenMensajeAccionJson>(objTransac,headers);
				
				ResponseEntity<GenMensajeAccionJson> result = restTemplate.exchange(uri,HttpMethod.PUT, request, GenMensajeAccionJson.class);
			    //Verify request succeed
				Integer status = result.getStatusCodeValue();
				if(status == HttpStatus.ACCEPTED.value()
					|| status == HttpStatus.CREATED.value()
					|| status == HttpStatus.OK.value()) {
					//GenMensajeAccionJson objResult =   result.getBody();
					return 1;
				}
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}	

		return 0;
	}

	@Override
	public ResultTx<GenMensajeAccionContextJson> enviarCorreo(GenMensajeAccionContextJson objTransac){
		int resultMsg = 0;
		if(UtilesCommons.noEsNulo(objTransac)  && 
				UtilesCommons.noEsNulo(objTransac.getObjMensajeAccion()) && 
				UtilesCommons.noEsNulo(objTransac.getListMensajeAccionDestino())) {
			
			String subject= objTransac.getObjMensajeAccion().getMsgAsunto();
			String mensajeFormateadoBody = objTransac.getObjMensajeAccion().getMsgCuerpomensajeHtml();
			String mensajeFormateadoBodyText = objTransac.getObjMensajeAccion().getMsgCuerpomensajeText();
			List<String> listCorreoDestinos = objTransac.getListMensajeAccionDestino().stream()
					.map(f -> f.getMsgadDestinoValor())
					.collect(Collectors.toList());
			resultMsg = emailGenericService.enviarCorreo(emailGenericService.getObjetoMail(listCorreoDestinos, subject, 
					mensajeFormateadoBody,mensajeFormateadoBodyText, null, new Date(), false));
	
		}					
		if(resultMsg > 0) {
			logger.info(UtilesRest.getMSJProperty("CORREOS_GEN.ENVIO_CORREO_EXITO"));
			return  ResultTx.ok(objTransac);
		}else {																
			if(resultMsg == -1) {
				logger.error(UtilesRest.getMSJProperty("CORREOS_GEN.ENVIO_CORREO_FALLO_PLANT_INVALIDA"));	
			}else if(resultMsg == -2) {
				logger.error(UtilesRest.getMSJProperty("CORREOS_GEN.ENVIO_CORREO_NO_ENVIO")+": " +"Sin Correos Destinos.");
			}else if(resultMsg == -3) {
				logger.error(UtilesRest.getMSJProperty("CORREOS_GEN.ENVIO_CORREO_FALLO_SUBJECT_INVALIDA"));
			}else if(resultMsg == -4) {
				logger.error(UtilesRest.getMSJProperty("CORREOS_GEN.ENVIO_CORREO_FALLO_MAIL_INVALIDA"));
			}else if(resultMsg == -5) {
				logger.error(UtilesRest.getMSJProperty("CORREOS_GEN.ENVIO_CORREO_NO_ENVIO"));
			}			
			return  ResultTx.error(objTransac,""+resultMsg);	
		}			
	}

	@Override
	public ResultTx<SysUsuarioJson> autorizarUsuarioEnv(String uriBase) {
		SysUsuarioJson objUsuario = null;
		try {
			String uri =uriBase + URI_AUTH_AUTORIZAR;
			RestTemplate restTemplate = new RestTemplate();
			UserCredentials filtro = new UserCredentials();
			String username = UtilesRest.getVariableEntornoSystemServer("PAR_USERNAME");
			String password = UtilesRest.getVariableEntornoSystemServer("PAR_PASSWORD");			
			filtro.setUsername(username);
			filtro.setPassword(password);
			HttpHeaders headers = new HttpHeaders();
			headers.set("content-type", "application/json;charset=UTF-8");
			HttpEntity<UserCredentials> requestAuth = new HttpEntity<UserCredentials>(filtro,headers);
			logger.info("AUTH: [USER]::"+username +":[PASS]:" + "****");
			ResponseEntity<SysUsuarioJson> resultAuth = restTemplate.postForEntity(uri, requestAuth, SysUsuarioJson.class);
			Integer statusAuth = resultAuth.getStatusCodeValue();			
			if(statusAuth == HttpStatus.ACCEPTED.value()
					|| statusAuth == HttpStatus.CREATED.value()
					|| statusAuth == HttpStatus.OK.value()) {
				objUsuario = resultAuth.getBody();					
				return ResultTx.ok(objUsuario);
			}			
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("autorizarUsuarioEnv",e);
		}
			
		return ResultTx.error(objUsuario, "0");
	}

	@Override
	public boolean validarToken(String uriBase, String token, Integer id) {
		boolean result = false;
		try {
			
			String uri =uriBase + URI_AUTH_BUSCAR_USUARIO_ID+id;
			RestTemplate restTemplate = new RestTemplate();		
			HttpHeaders headers = new HttpHeaders();
			headers.set("content-type", "application/json;charset=UTF-8");
			headers.set(Constant.JWT_TOKEN_HEADER_PARAM, Constant.JWT_TOKEN_PREFIX+" "+token );		
			HttpEntity<UserCredentials> requestAuth = new HttpEntity<UserCredentials>(headers);		
			ResponseEntity<SysUsuarioJson> resultAuth = restTemplate.exchange(uri,HttpMethod.GET, requestAuth, SysUsuarioJson.class);
			Integer statusValidacion = resultAuth.getStatusCodeValue();
			if(statusValidacion == HttpStatus.ACCEPTED.value()
					|| statusValidacion == HttpStatus.CREATED.value()
					|| statusValidacion == HttpStatus.OK.value()) {
				result = true;
			}			
		}catch(HttpClientErrorException e) {
			Integer status =  e.getRawStatusCode();
			 if(status == HttpStatus.FORBIDDEN.value()
						|| status == HttpStatus.UNAUTHORIZED.value()) {
				
				 result = false;
			}
			 
		}catch(Exception e) {
			result = false;
		}
		return result;
	}
}

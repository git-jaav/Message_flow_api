package pe.jaav.sistemas.messageFlowApi.util;

import java.util.ResourceBundle;

import pe.jaav.common.util.UtilesCommons;

public class UtilesRest {

	public static final String LOCALE_CODIGO_SPAIN="_es";
	public static final String LOCALE_CODIGO_ENGLISH="_en";
	public static final String LOCALE_CODIGO_ITALIAN="_it";
	
	
	public static final String SI_db="S";
	public static final String NO_db="N";	
	
	public static ResourceBundle applicationProp = ResourceBundle.getBundle("application");
	public static ResourceBundle propiedades = ResourceBundle.getBundle("messages");

	public static String getMSJProperty(String prop){	
		//propiedades.getLocale().setDefault(FacesContext.getCurrentInstance().getViewRoot().getLocale());
		//propiedades = ResourceBundle.getBundle("mensajes",FacesUtil.getContextLocale());
		if(propiedades.containsKey(prop)){
			return propiedades.getString(prop);	
		}else{
			return "";
		}				
	}
	
	public static String getAppProperty(String prop){		
		if(applicationProp.containsKey(prop)){
			return applicationProp.getString(prop);	
		}else{
			return "";
		}				
	}
	
	public static String getPropertyParametrosSystemServer(String prop){
		if(applicationProp.containsKey(prop)){
			//String globalConfigFile = System.getProperty(applicationProp.getString(prop));						
			String globalConfigFile = System.getenv(applicationProp.getString(prop));
			return globalConfigFile!=null?globalConfigFile:"";	
		}else{
			return "";
		}				
	}

	
	public static String getVariableEntornoSystemServer(String parEnv){
		if(UtilesCommons.noEsVacio(parEnv)){								
			String globalConfigFile = System.getenv(parEnv);
			return globalConfigFile!=null?globalConfigFile:"";	
		}else{
			return "";
		}				
	}
	
	/** URL EXTERNA
	 * @return
	 */
	public static String getParamUrlBaseiAPIConsulta() {
		String paramUrlBaseAPiConsulta = "";
		String urlBase = "";
		urlBase = getPropertyParametrosSystemServer("URL_SERVICE_REST_API_CONSULTA_PROP");
		if(UtilesCommons.esVacio(urlBase)){
			urlBase = getAppProperty("URL_SERVICE_REST_API_CONSULTA");	
		}	
		paramUrlBaseAPiConsulta = urlBase;
		return paramUrlBaseAPiConsulta;
	}
	
	public static String getMsgResultCorreo(int reusltMsg){
		String mensaje = "";
		/**4: MOSTRAR MENSAJES */
		switch (reusltMsg){
			case 1: mensaje = getMSJProperty("CORREOS_GEN.ENVIO_CORREO_EXITO");				
				break;
			case 0: mensaje = getMSJProperty("CORREOS_GEN.ENVIO_CORREO_FALLO");
				break;
			case -1: mensaje = getMSJProperty("CORREOS_GEN.ENVIO_CORREO_FALLO_PLANT_INVALIDA");
				break;
			case -2: mensaje = getMSJProperty("CORREOS_GEN.ENVIO_CORREO_FALLO_MAIL_INVALIDA");
				break;
			case -3: mensaje = getMSJProperty("CORREOS_GEN.ENVIO_CORREO_FALLO_SUBJECT_INVALIDA");
				break;
			case -4: mensaje = getMSJProperty("CORREOS_GEN.ENVIO_CORREO_FALLO_NO_BUILD");
				break;
			case -5: mensaje = getMSJProperty("CORREOS_GEN.ENVIO_CORREO_NO_ENVIO");
				break;		
			default : mensaje = "error";
		}				
		
		return mensaje;
	}
}

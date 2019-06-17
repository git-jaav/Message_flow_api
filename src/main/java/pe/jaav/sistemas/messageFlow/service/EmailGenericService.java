package pe.jaav.sistemas.messageFlow.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import pe.jaav.common.jmail.model.Email;

public interface EmailGenericService {

	public int enviarCorreoMixto(List<Email> listaObjCorreo);
	
	/**Env|a el correo electr|nico de acuerdo a los par|metros estandar  de correo
	 * @param objCorreo
	 * email: el objeto del Email (con los par|metros del correo)	
	 * @return
	 * 1:	si el env|o fu exitos
	 * 0:	si hubo una excepci|n
	 * -1:	Si no se recibi| una plantilla v|lida
	 * -2:	No se encontraron correos destino
	 * -3:	No se encontr| un motivo del correo (SUBJECT)
	 * -4:	No se recibi| el objeto de Correo. (Alg|n par|metro err|neo)
	 * -5:	No se pudo enviar el correo. Sucedi| un error en el env|o.	 * 
	 */	
	public int enviarCorreo(Email objCorreo);
	
	
	/**
	 * @param objCorreo
	 * email: el objeto del Email (con los par|metros del correo)
	 * @param procesoPlantilla
	 * procesoPlantilla: El c|digo del Proceso al que la plantilla est| vinculado.
	 * @return
	 * 1:	si el env|o fu exitos
	 * 0:	si hubo una excepci|n
	 * -1:	Si no se recibi| una plantilla v|lida
	 * -2:	No se encontraron correos destino
	 * -3:	No se encontr| un motivo del correo (SUBJECT)
	 * -4:	No se recibi| el objeto de Correo. (Alg|n par|metro err|neo)
	 * -5:	No se pudo enviar el correo. Sucedi| un error en el env|o.		  
	 */
	//public int enviarCorreo(Email objCorreo,String procesoPlantilla);
		
	public Email getObjetoMail(List<String> listCorreoDestinos,String subject, String body, String textBody,
			List<String> listPathFileAtached, Date correoFecha,boolean contienePlantilla);
	
	/** Crea y retorna el  objeto Gen|rico del correo.
	 * @param listCorreoDestinos
	 * Lista de los correos destino
	 * @param subject
	 * EL tema del correo
	 * @param bodyPlantilla
	 * El mensaje del correo (Versi|n plantilla o versi|n texto)
	 * @param listPathFileAtached
	 * Lista de los PATH de los archivos adjuntos
	 * @param correoFecha
	 * fecha del correo de env|o
	 * @param mapvariablesValores
	 * Map conteniendo las variables del correo y sus respectivos valores
	 * @return
	 * El objeto del email que conteniendo los par|metros.
	 */
	public Email getObjetoMail(List<String> listCorreoDestinos,String subject, String bodyPlantilla, 
			List<String> listPathFileAtached, Date correoFecha , Map<String,String> mapvariablesValores);
	
}

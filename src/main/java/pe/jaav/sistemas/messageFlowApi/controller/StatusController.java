package pe.jaav.sistemas.messageFlowApi.controller;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.jaav.common.util.UtilesCommons;
import pe.jaav.sistemas.messageFlowApi.util.UtilesRest;
import pe.jaav.sistemas.utiles.Constant;




@RestController
@CrossOrigin
@RequestMapping("status")
public class StatusController {

	
	@GetMapping(value="verificar")
	public ResponseEntity<String> verificarStatus(){
		return new ResponseEntity<String>(Constant.RESULT_OK, HttpStatus.OK);
	}
	

	@GetMapping(value="time")
	public ResponseEntity<String> verificarStatusTime(){
		String timeZoneCode ="";
		String envVarTimeZone = UtilesRest.getPropertyParametrosSystemServer("jaav.timezone.param.envcode");
		if(UtilesCommons.noEsVacio(envVarTimeZone)) {		
			timeZoneCode = "TZ-CODE:"+envVarTimeZone;
		}		
		Date fechaActual = new Date();
		String fecha = fechaActual.toString();
		if(fecha != null){
			return new ResponseEntity<String>(timeZoneCode +"::"+fecha, HttpStatus.OK);	
		}else{
			return new ResponseEntity<String>(Constant.RESULT_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}

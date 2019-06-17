package pe.jaav.sistemas.messageFlowApi.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import pe.jaav.common.util.UtilesCommons;
import pe.jaav.sistemas.messageFlowApi.util.UtilesRest;


/**
 * Componente para Calendarizar y programar el proceso a ejecutar
 * @author araucoj
 *
 */
@Component
public class SchedulingComponent {


	/**Log*/
	private static final Logger logger = LoggerFactory.getLogger(SchedulingComponent.class);
	
	@Autowired
	private ThreadPoolTaskScheduler taskSchedule;
	

	@Autowired
	private ProcesoSendEmailRunnable procesoSendEmail;
	
	private String tokenSecurity;
	private Integer IdSecurity;
	
	/**
	 * Inicializar este metodo al iniciar la ejecucion del contexto del proyecto
	 */
	@EventListener(ContextRefreshedEvent.class)
	public void contextRefreshedEvent() {
		if(UtilesRest.SI_db.equals(UtilesRest.getAppProperty("config.schedule.onstart.ejecutar"))){
			ejecutarProcesos();	
		}else{
			System.out.println("Tareas scheduling inhabilitadas...");
		}
	}
	  
	/**
	 *  Ejecutar procesos
	 */
	public void ejecutarProcesos() {	
		try{
		//			taskExecutor.schedule(new ProcesoDaemonRunnable("Esta corriendo el hilo	"),
		//			new Date(System.currentTimeMillis() + 3000));
		//	taskExecutor.scheduleAtFixedRate(new ProcesoDaemonRunnable("Esta corriendo el hilo	"),
		//			2000);
			//ejecutarProcesoTest();			
			ejecutarProcesoSendEmailTimmingDelay();			
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		
	}
	

	/**
	 * Procesos a ejecutar con la PROGRAMACION del Schedule
	 */
	public void ejecutarProcesoTest(){
		long tiempoDelay  = 4000;		
	
		/**ejecutar (scheduleAtFixedRate: implica ejecutar cada periodo de tiempo especificado, 
		 * sin importar si el proceso anterior haya acabado o no.
		 * Ej: Cada 4 segundos
		 */				
		taskSchedule.scheduleAtFixedRate(procesoSendEmail,tiempoDelay);
	}

	public void ejecutarProcesoSendEmailTimmingDelay(){		
		//ejecutar con Configuracion tiempo DELAY
		
		String timeDefault = UtilesRest.getPropertyParametrosSystemServer("config.schedule.timing.sendemail_prop");		
		if(UtilesCommons.esVacio(timeDefault)) {
			timeDefault = UtilesRest.getAppProperty("config.schedule.timing.sendemail_def"); //#media noche: hasta las 12 y 05	
		}
		long  tiempoDelay = UtilesCommons.noEsVacio(timeDefault)? Long.parseLong(timeDefault):40000; //default
		
		procesoSendEmail.setNombreRunnable("ProcesoSendEmailRunnable");
		procesoSendEmail.setTimeMilliSecond(tiempoDelay);
		procesoSendEmail.setComponentController(this);				
		taskSchedule.scheduleAtFixedRate(procesoSendEmail,tiempoDelay);
	}	
	
	/**
	 * Proceso que llevara a cabo la verificacion y ejecucion de proceso de pago
	 * para los contrato matriculas que aun estuvieran pendientes de procesar
	 * Este debera ser ejecutado, de preferencia ANTES del PROCESO de LIMPIAR los CONTRATOS NO PAGADOS
	 */
	public void ejecutarProcesoSendEmail(){
		/**Ver mas info sobre CRON notacion*/
		//http://www.quartz-scheduler.org/documentation/quartz-2.x/tutorials/crontrigger.html				
		String cronConfig = getCronExpresionSendEmail("SCHED_SENDEMAIL");
		//ejecutar con Configuracion CRON
		CronTrigger cronTrigger = new CronTrigger(cronConfig);
		procesoSendEmail.setNombreRunnable("ProcesoSendEmailRunnable");
		procesoSendEmail.setCronExpresion(cronConfig);
		procesoSendEmail.setComponentController(this);		
		taskSchedule.schedule(procesoSendEmail,cronTrigger);
				
		//ejecutar con Configuracion tiempo DELAY
		//long tiempoDelay  = 10000;
		//taskSchedule.scheduleAtFixedRate(procesoDaemon,tiempoDelay);
	}	
	
	/**
	 * @param paramClave
	 * @return
	 */
	public String getCronExpresionSendEmail(String paramEnv){		
		/**Ver mas info sobre CRON Expresion*/
		//http://www.quartz-scheduler.org/documentation/quartz-2.x/tutorials/crontrigger.html
		/**leyenda CRON:
		 * Orden {* * * * * * *}: [Seconds] [Minutes] [Hours] [Day of month] [Month] [Day of week] [Year] 
		 * */						
		//String cronTest1 = "0 0 0 * * MON-SAT"; //Cada Media noche de lunes a sabado
		//String cronTest2 = "0/30 30-40 16-17 * * MON-FRI"; //cada 30 seg, del min 30 a 40 de las horas 16 a 17 todos los meses de lunes a viernes		
		String cronDefault = UtilesRest.getAppProperty("config.schedule.cron.sendemail_def"); //#media noche: hasta las 12 y 05		
		String cronResult = cronDefault;
			
		
		return cronResult;
	}

	public String getTokenSecurity() {
		return tokenSecurity;
	}

	public void setTokenSecurity(String tokenSecurity) {
		this.tokenSecurity = tokenSecurity;
	}

	public Integer getIdSecurity() {
		return IdSecurity;
	}

	public void setIdSecurity(Integer idSecurity) {
		IdSecurity = idSecurity;
	}
	
	
	
	
}

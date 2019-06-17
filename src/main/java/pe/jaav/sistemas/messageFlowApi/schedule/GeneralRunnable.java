package pe.jaav.sistemas.messageFlowApi.schedule;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pe.jaav.common.datetime.TimeCommons;
import pe.jaav.common.util.UtilesCommons;

public class GeneralRunnable implements Runnable {

	/**Constantes*/
	public static final String MSJG_TEST = "[mensaje-running]"; 
	public static final String NOMBRE_DEFAULT = "runnable-def";	
	public static final int HORAS_ESPERA_ELIMINAR_def = 24;
	
	
	/**Variables*/
	
	protected ExecutorService ejecutorProcces = null;
	protected String mensajeTest;
	protected String nombreRunnable;		
	protected String cronExpresion;
	protected boolean indicaOn;
	protected int countCicloActual;
	protected long timeMilliSecond;
	
	protected SchedulingComponent componentController;
	
	/**
	* Default, iniciar como Spring component
	*/
	public GeneralRunnable() {		
		indicaOn = true;
		this.countCicloActual = 0;
		this.mensajeTest = MSJG_TEST;	
		this.nombreRunnable =  NOMBRE_DEFAULT;
	}
	
	

	/** Para test con un mensaje en particular
	 * @param mensajeTest
	 */
	public GeneralRunnable(String mensajeTest) {
		super();
		indicaOn = true;
		this.countCicloActual = 0;
		this.mensajeTest = mensajeTest;		
	}
	
	/** para determinar el DELAY en pruebas
	 * @param timeMilliSecond
	 */
	public GeneralRunnable(long timeMilliSecond) {
		super();
		this.ejecutorProcces = Executors.newFixedThreadPool(10);
		this.indicaOn = false;
		this.countCicloActual = 0;
		this.timeMilliSecond = timeMilliSecond;
	}	
	
	

	/**
	 * para iniciar la ejecucion del Hilo por separado de la Config del SCHEDULIN TASK
	 */
	public void iniciarEjecucion(){
		indicaOn = true;
		ejecutorProcces.execute(this);				
	}



	@Override
	public void run() {
		countCicloActual++;
		ejecutarModoTest();		
		
//		while(indicaOn){
//			Thread.sleep(timeMilliSecond);
//			countCicloActual++;
//			ejecutarModoTest();												
//			if(!indicaOn){
//				getEjecutorProcces().shutdown();
//			}			
//		}	
	}

	/****** PROCESOS RUNNBLES ******/
	
	
	/**
	 * Procesos Test
	 */
	public void ejecutarModoTest() {
		System.out.println(getMensajeCicloProceso());
	}

	/**
	 * @return
	 */
	public String getMensajeCicloProceso() {
		return mensajeTest+"::["+countCicloActual+"]:"+				
				":["+nombreRunnable+"]"+
				(UtilesCommons.noEsVacio(cronExpresion)?(":["+cronExpresion+"]:"):"")+
				":["+Thread.currentThread().getName()+"]"+
				":["+TimeCommons.imprimirDateTime(new Date(), "yyyy-MM-dd HH:mm:ss")+"]"
				;
	}



	public ExecutorService getEjecutorProcces() {
		return ejecutorProcces;
	}



	public void setEjecutorProcces(ExecutorService ejecutorProcces) {
		this.ejecutorProcces = ejecutorProcces;
	}



	public String getMensajeTest() {
		return mensajeTest;
	}



	public void setMensajeTest(String mensajeTest) {
		this.mensajeTest = mensajeTest;
	}



	public String getNombreRunnable() {
		return nombreRunnable;
	}



	public void setNombreRunnable(String nombreRunnable) {
		this.nombreRunnable = nombreRunnable;
	}



	public String getCronExpresion() {
		return cronExpresion;
	}



	public void setCronExpresion(String cronExpresion) {
		this.cronExpresion = cronExpresion;
	}



	public boolean isIndicaOn() {
		return indicaOn;
	}



	public void setIndicaOn(boolean indicaOn) {
		this.indicaOn = indicaOn;
	}



	public int getCountCicloActual() {
		return countCicloActual;
	}



	public void setCountCicloActual(int countCicloActual) {
		this.countCicloActual = countCicloActual;
	}



	public long getTimeMilliSecond() {
		return timeMilliSecond;
	}



	public void setTimeMilliSecond(long timeMilliSecond) {
		this.timeMilliSecond = timeMilliSecond;
	}



	public SchedulingComponent getComponentController() {
		return componentController;
	}



	public void setComponentController(SchedulingComponent componentController) {
		this.componentController = componentController;
	}
	
	
	
}

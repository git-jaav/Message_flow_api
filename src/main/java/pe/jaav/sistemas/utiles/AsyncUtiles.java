package pe.jaav.sistemas.utiles;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.function.Consumer;
import java.util.function.Function;

public class AsyncUtiles {

	
	/**
	 *  Numero de Hilos en el Pool activo por defecto.
	 */
	public static final Integer THREAD_NUM_def = 3;
	
	/**
	 * @param function
	 * @param objParamFunction
	 */
	public static <T,R> void  ejecutarFuncionAsync(Function<T, R>  function, T objParamFunction ){
		
		
	}
	
    /**  Permite Ejecutar una accion 
     *  (A TRAVES DE UN METODO, que recibe un parametor de tipo T,  enviado por parametros)
     * @param consume
     * @param objParamConsume
     */
    public static <T> void ejecutarAccionAsync(Consumer<T>  consume, T objParamConsume ){
    	try{	   	 
	        //Preparar los FUTURETASKS
	        int threadNum = THREAD_NUM_def;
	        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
	        List<FutureTask<Integer>> taskList = new ArrayList<FutureTask<Integer>>();
	 
	        //Iniciar un hilo de Ejecucion
	        FutureTask<Integer> futureTask_1 = new FutureTask<Integer>(new Callable<Integer>() {
	            @Override
	            public Integer call() {
	            	consume.accept(objParamConsume);
	            	return 1;	                
	            }
	        });
	        //terminar Ejecucion
	        taskList.add(futureTask_1);
	        executor.execute(futureTask_1);	        		        	 		 	       
	        executor.shutdown();		 		        
    	}catch(Exception e){
    		e.printStackTrace();
    	}

    }
    
    
    /**  Permite Ejecutar una accion 
     *  (A TRAVES DE UN METODO, que recibe un parametor de tipo T,  enviado por parametros)
     * @param consume
     * @param objParamConsume
     */
    public static <T> void ejecutarAccionAsync(Consumer<T>  consume, T objParamConsume ,Function<T,Integer> functionReturn){
    	try{	   	 
	        //Preparar los FUTURETASKS
	        int threadNum = THREAD_NUM_def;
	        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
	        List<FutureTask<Integer>> taskList = new ArrayList<FutureTask<Integer>>();
	 
	        //Iniciar un hilo de Ejecucion
	        FutureTask<Integer> futureTask_1 = new FutureTask<Integer>(new Callable<Integer>() {
	            @Override
	            public Integer call() {
	            	consume.accept(objParamConsume);
	            	return functionReturn.apply(objParamConsume);	                
	            }
	        });
	        //terminar Ejecucion
	        taskList.add(futureTask_1);
	        executor.execute(futureTask_1);	        		        	 		 	       
	        executor.shutdown();		 		        
    	}catch(Exception e){
    		e.printStackTrace();
    	}

    }
}

package pe.jaav.sistemas.messageFlowApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import pe.jaav.common.util.UtilesCommons;
import pe.jaav.sistemas.messageFlowApi.util.UtilesRest;


@Configuration
public class SchedulingConfig {

	private static final int POOL_SIZE = (
			UtilesCommons.noEsVacio(UtilesRest.getAppProperty("config.schedule.pull_size"))?
					Integer.parseInt(UtilesRest.getAppProperty("config.schedule.pull_size")):
					10);
	
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler
          = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(POOL_SIZE);
        threadPoolTaskScheduler.setThreadNamePrefix(
          "my-scheduled-task-pool-");
        return threadPoolTaskScheduler;
    }
    
}

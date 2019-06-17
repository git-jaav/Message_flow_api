package pe.jaav.sistemas.messageFlowApi;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import pe.jaav.common.util.UtilesCommons;
import pe.jaav.sistemas.messageFlowApi.util.UtilesRest;

@SpringBootApplication(scanBasePackages={"pe.jaav.sistemas"},exclude = JpaRepositoriesAutoConfiguration.class)
//@EnableTransactionManagement
@EnableConfigurationProperties
@EnableScheduling
@EnableCaching
//@EnableJpaRepositories
public class MessageFlowApiApplication {

	@PostConstruct
	public void init(){
		/** Obtener Variable de Zona loca si hubiera
		 * Ej: UCT, GMT , COT , etc. */
		String envVarTimeZone = UtilesRest.getPropertyParametrosSystemServer("jaav.timezone.param.envcode");
		if(UtilesCommons.noEsVacio(envVarTimeZone)) {
			TimeZone.setDefault(TimeZone.getTimeZone(envVarTimeZone));	
		}		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MessageFlowApiApplication.class, args);
	}

}

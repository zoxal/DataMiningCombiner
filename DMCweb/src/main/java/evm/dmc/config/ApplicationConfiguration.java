package evm.dmc.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import evm.dmc.service.testing.Service;
import evm.dmc.service.testing.ServiceProperties;

@EnableConfigurationProperties(ServiceProperties.class)
@EnableAutoConfiguration
@ComponentScan
public class ApplicationConfiguration {

	@Autowired
	BeanFactory beanFactory;
	
	@Bean
    public Service service(ServiceProperties properties) {
        return new Service(properties.getMessage());
    }
	
	@Bean
	public ExecutorService executorService() {
		return Executors.newCachedThreadPool();
		// new ThreadPoolExecutor
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
}

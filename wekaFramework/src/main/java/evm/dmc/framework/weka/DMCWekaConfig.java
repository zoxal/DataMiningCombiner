package evm.dmc.framework.weka;

import evm.dmc.framework.base.DMCCoreConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("evm.dmc.weka")
@Import(DMCCoreConfig.class)
@PropertySource("classpath:weka.properties")
public class DMCWekaConfig {

}

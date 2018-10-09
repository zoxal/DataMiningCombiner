package evm.dmc.config.devel;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.web.context.WebApplicationContext;

/**
 * Configuration class for initializing of database based on
 * MySQL database
 */
@Configuration
@Profile("devMySQL")
public class DbDataInitializerMySQL {
private final String SAMPLE_DATA = "classpath:data-mysql-devel.sql";
	
	@Autowired
	private DataSource datasource;
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@PostConstruct
	public void loadIfInMemory() throws Exception {
		Resource resource = webApplicationContext.getResource(SAMPLE_DATA);
		ScriptUtils.executeSqlScript(datasource.getConnection(), resource);
	}
}

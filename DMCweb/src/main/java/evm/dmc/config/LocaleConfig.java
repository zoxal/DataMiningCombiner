package evm.dmc.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * Configuration class for localization feature
 */
@Configuration
public class LocaleConfig extends WebMvcConfigurerAdapter {

	/**
	 * In order for our application to be able to determine which locale is
	 * currently being used, we need to add a LocaleResolver bean:
	 */
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.US);
		return slr;
	}

	/**
	 * We need to add an interceptor bean that will switch to a new locale based
	 * on the value of the lang parameter appended to a request:
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	/**
	 * Adds LocaleChangeInterceptor bean to the InterceptorRegistry bean
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(localeChangeInterceptor());
	}
}

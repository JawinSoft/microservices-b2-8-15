package com.spring.boot.msk.common.config;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.spring.boot.msk.common.aop.MethodExceutionTimeAspect;
import com.spring.boot.msk.common.filter.LogProcessingTimeFilter;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class ApplicationCommonConfiguration {

	@Value("${app-title}")
	private String appTitle;

	@Value("${app-description}")
	private String appDescription;

	@Bean
	public MethodExceutionTimeAspect methodExceutionTimeAspect() {
		return new MethodExceutionTimeAspect();
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().components(new Components()).info(new Info().title(appTitle).description(appDescription));
	}

	@Bean
	public Filter logProcessingTimeFilter() {
		return new LogProcessingTimeFilter();
	}

	@Bean
	public FilterRegistrationBean<Filter> registerLogProcessingTimeFilter() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(logProcessingTimeFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:error-messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

}

package it.ms.template.presentation.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;

@EnableAutoConfiguration
@ComponentScan("it.ms.template.presentation")
@Import({ SwaggerConfig.class })
public class RootApplicationConfig extends WebMvcConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(RootApplicationConfig.class);

	@Autowired
	private Environment environment;

	@Bean
	public Jaxb2RootElementHttpMessageConverter jaxb2RootElementHttpMessageConverter() {

		return new Jaxb2RootElementHttpMessageConverter();
	}

	@Bean
	public MappingJackson2HttpMessageConverter jsonMessageConverter() {

		MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		jackson2HttpMessageConverter.setObjectMapper(objectMapper());
		return jackson2HttpMessageConverter;
	}

	@Bean
	public ObjectMapper objectMapper() {

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JodaModule());
		mapper.registerModule(new GuavaModule());
		return mapper;
	}
}

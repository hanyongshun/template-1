package it.ms.template.presentation.config;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.util.UriComponentsBuilder;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.controllers.DefaultSwaggerController;
import com.mangofactory.swagger.paths.SwaggerPathProvider;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;

@Configuration
@EnableSwagger
@PropertySource({ "classpath:swagger.properties" })
public class SwaggerConfig {

	private static final String SWAGGER_INCLUDE_PATTERNS = "swagger.include.patterns";
	private static final String SWAGGER_SWAGGER_GROUP = "swagger.group";
	private static final String SWAGGER_API_VERSION = "swagger.api.version";
	private static final String SWAGGER_URL = "swagger.url";

	private static final String SWAGGER_TITLE = "swagger.title";
	private static final String SWAGGER_DESCRIPTION = "swagger.description";
	private static final String SWAGGER_TERMSOFSERVICEURL = "swagger.termsOfServiceUrl";
	private static final String SWAGGER_CONTACT = "swagger.contact";
	private static final String SWAGGER_LICENSE = "swagger.license";
	private static final String SWAGGER_LICENSEURL = "swagger.licenseUrl";

	@Autowired
	private SpringSwaggerConfig springSwaggerConfig;

	@Autowired
	private Environment environment;

	@Bean
	public SwaggerSpringMvcPlugin customImplementation() {

		return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apiInfo())
				.includePatterns(environment.getProperty(SWAGGER_INCLUDE_PATTERNS))
				.swaggerGroup(environment.getProperty(SWAGGER_SWAGGER_GROUP))
				.apiVersion(environment.getProperty(SWAGGER_API_VERSION)).pathProvider(getSwaggerPathProvider());
	}

	private ApiInfo apiInfo() {

		ApiInfo apiInfo = new ApiInfo(environment.getProperty(SWAGGER_TITLE),
				environment.getProperty(SWAGGER_DESCRIPTION), environment.getProperty(SWAGGER_TERMSOFSERVICEURL),
				environment.getProperty(SWAGGER_CONTACT), environment.getProperty(SWAGGER_LICENSE),
				environment.getProperty(SWAGGER_LICENSEURL));
		return apiInfo;
	}

	@Bean
	public SwaggerPathProvider getSwaggerPathProvider() {

		return new SwaggerPathProvider() {
			@Autowired
			private ServletContext servletContext;

			@Override
			protected String applicationPath() {
				return getAppRoot().build().toString();
			}

			@Override
			protected String getDocumentationPath() {
				return getAppRoot().path(DefaultSwaggerController.DOCUMENTATION_BASE_PATH).build().toString();
			}

			private UriComponentsBuilder getAppRoot() {
				return UriComponentsBuilder.fromHttpUrl(environment.getProperty(SWAGGER_URL)).path(
						servletContext.getContextPath());
			}
		};
	}
}

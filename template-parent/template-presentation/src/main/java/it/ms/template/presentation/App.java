package it.ms.template.presentation;

import it.ms.template.presentation.config.RootApplicationConfig;
import it.ms.template.template_parent.main.MainSupport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class App extends MainSupport {

	public App(String[] args) {
		super(args);
	}

	public static void main(String[] args) throws Exception {
		new App(args);
	}

	@Override
	public void initContext() throws Exception {
		SpringApplication.run(RootApplicationConfig.class);
	}
}

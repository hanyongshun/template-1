package it.ms.template.template_parent.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public abstract class AnnotationConfigApplicationSupport extends ApplicationSupport {

	protected AnnotationConfigApplicationSupport(String[] args) {
		super(args);
	}

	protected void configureContext() {

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.getEnvironment().setActiveProfiles(profileParameter.getActiveProfile());
		applicationContext.register(getJavaConfigFile());
		applicationContext.refresh();
		applicationContext.registerShutdownHook();
		this.applicationContext = applicationContext;
	}

	public abstract Class<?> getJavaConfigFile();

}

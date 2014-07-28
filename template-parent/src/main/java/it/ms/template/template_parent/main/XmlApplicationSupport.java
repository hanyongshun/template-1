package it.ms.template.template_parent.main;

import org.springframework.context.support.GenericXmlApplicationContext;

public abstract class XmlApplicationSupport extends ApplicationSupport {

	protected XmlApplicationSupport(final String[] args) {
		super(args);
	}

	protected void configureContext() {
		GenericXmlApplicationContext applicationContext = new GenericXmlApplicationContext();
		applicationContext.getEnvironment().setActiveProfiles(profileParameter.getActiveProfile());
		applicationContext.load(getSpringConfigFiles());
		applicationContext.refresh();
		applicationContext.registerShutdownHook();
		this.applicationContext = applicationContext;
	}

	public abstract String[] getSpringConfigFiles();

}

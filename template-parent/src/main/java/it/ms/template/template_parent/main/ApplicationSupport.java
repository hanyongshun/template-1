package it.ms.template.template_parent.main;

import org.springframework.context.ApplicationContext;

public abstract class ApplicationSupport extends MainSupport {

	protected ApplicationContext applicationContext;

	protected ApplicationSupport(final String[] args) {
		super(args);
	}

	@Override
	public void initContext() throws Exception {
		beforeContext();
		configureContext();
		afterContext();
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	protected abstract void configureContext();

	protected void beforeContext() {
	}

	protected void afterContext() {
	}
}

package it.ms.template.template_parent.main;

import it.ms.template.template_parent.main.parameter.PortParameter;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public abstract class WebSupport extends MainSupport {

	private static final String WEBAPP_RESOURCE = "WEB-INF/classes";
	private static final Integer DEFAULT_SERVER_PORT_VALUE = 8080;

	private PortParameter portParameter;

	protected WebSupport(final String[] args) {
		super(args);
	}

	@Override
	protected void addParameterHandler() {
		super.addParameterHandler();
		portParameter = createPortParameter(getServerPort());
	}

	@Override
	public void initContext() throws Exception {
		WebAppContext webapp = setUpWebContext();
		addExtensionToWebAppContext(webapp);
		startServer(webapp);
	}

	private WebAppContext setUpWebContext() {
		String webDir = this.getClass().getClassLoader().getResource(getWebappResources()).toExternalForm();

		WebAppContext webapp = new WebAppContext();
		webapp.setContextPath(getContextPath());
		webapp.setResourceBase(webDir);
		webapp.setParentLoaderPriority(true);
		return webapp;
	}

	private void startServer(WebAppContext webapp) throws Exception {
		Server server = new Server(portParameter.getPort());
		server.setHandler(webapp);
		server.start();
		server.join();
	}

	protected void addExtensionToWebAppContext(WebAppContext webapp) {
	}

	protected Integer getServerPort() {
		return DEFAULT_SERVER_PORT_VALUE;
	}

	protected String getWebappResources() {
		return WEBAPP_RESOURCE;
	}

	protected abstract String getContextPath();
}

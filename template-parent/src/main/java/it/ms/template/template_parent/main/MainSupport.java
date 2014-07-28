package it.ms.template.template_parent.main;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beust.jcommander.JCommander;

import it.ms.template.template_parent.main.parameter.HostParameter;
import it.ms.template.template_parent.main.parameter.ParameterHandler;
import it.ms.template.template_parent.main.parameter.PortParameter;
import it.ms.template.template_parent.main.parameter.ProfileParameter;

public abstract class MainSupport {

	private static final Logger logger = LoggerFactory.getLogger(MainSupport.class);

	private Set<ParameterHandler> handlers = new HashSet<>();
	protected ProfileParameter profileParameter;

	public MainSupport(final String[] args) {
		startUpContext(args);
	}

	private void startUpContext(String[] args) {
		try {
			logger.info("Starting " + this.getClass().getName());

			addParameterHandler();
			parseArguments(args);
			logConfiguration();
			initContext();

			logger.info("Startup Complete");

		} catch (Exception ex) {
			logger.info(ex.getMessage());
			System.exit(0);
		}
	}

	protected void addParameterHandler() {
		profileParameter = createProfileParameter();
	}

	private void parseArguments(String[] args) throws Exception {
		JCommander jCommander = new JCommander(handlers);
		try {
			jCommander.parse(args);
		} catch (Exception e) {
			jCommander.usage();
			throw e;
		}
		runParameterActions();
	}

	public abstract void initContext() throws Exception;

	private void logConfiguration() {
		for (ParameterHandler handler : handlers) {
			logger.info(handler.toString());
		}
	}

	private void runParameterActions() {
		for (ParameterHandler handler : handlers) {
			handler.parameterAction();
		}
	}

	private ProfileParameter createProfileParameter() {
		ProfileParameter profileParameter = new ProfileParameter();
		handlers.add(profileParameter);
		return profileParameter;
	}

	protected PortParameter createPortParameter(Integer... port) {

		PortParameter portParameter;
		if (port.length == 1) {
			portParameter = new PortParameter(port[0]);
		} else if (port.length == 0) {
			portParameter = new PortParameter();
		} else {
			throw new IllegalArgumentException("Zero or one port is allowed!");
		}
		handlers.add(portParameter);
		return portParameter;
	}

	protected HostParameter createHostParameter(String... hosts) {

		HostParameter hostParameter;
		if (hosts.length == 1) {
			hostParameter = new HostParameter(hosts[0]);
		} else if (hosts.length == 0) {
			hostParameter = new HostParameter();
		} else {
			throw new IllegalArgumentException("Zero or one host is allowed!");
		}
		handlers.add(hostParameter);
		return hostParameter;
	}
}

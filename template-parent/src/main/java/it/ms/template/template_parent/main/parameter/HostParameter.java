package it.ms.template.template_parent.main.parameter;

import com.beust.jcommander.Parameter;

public class HostParameter implements ParameterHandler {

	@Parameter(names = { "-h", "--host" }, description = "Host address")
	private String host = "127.0.0.1";

	public HostParameter() {
	}

	public HostParameter(String host) {
		this.host = host;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public void parameterAction() {
	}

	public String toString() {
		return "Startup host : " + getHost();
	}
}

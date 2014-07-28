package it.ms.template.template_parent.main.parameter;

import com.beust.jcommander.IValueValidator;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

public class PortParameter implements ParameterHandler {
	@Parameter(names = { "-p", "--port" }, description = "Jetty port [1-65535]", validateValueWith = PortValueValidator.class)
	private Integer port = 8080;

	public PortParameter() {
	}

	public PortParameter(Integer port) {
		this.port = port;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	@Override
	public void parameterAction() {
	}

	public String toString() {
		return "Startup Port : " + getPort();
	}

	public static class PortValueValidator implements IValueValidator<Integer> {
		@Override
		public void validate(String name, Integer value) throws ParameterException {
			if (value < 1 || value > 65535) {
				throw new ParameterException(String.format("Invalid port %s. [1-65535]", value));
			}
		}
	}

}

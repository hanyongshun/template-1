package it.ms.template.template_parent.ddd.model;

public abstract class ID extends ValueObject {

	private final String value;

	protected ID(final String value) {

		this.value = value;
	}

	protected ID() {

		// for serialization purposes
		this.value = null;
	}

	protected String value() {

		return value;
	}
}

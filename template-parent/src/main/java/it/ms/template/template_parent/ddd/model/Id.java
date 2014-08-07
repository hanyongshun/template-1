package it.ms.template.template_parent.ddd.model;

import java.util.UUID;

public abstract class ID extends ValueObject {

	private final UUID value;

	protected ID(final UUID value) {

		this.value = value;
	}

	protected ID() {

		// for serialization purposes
		this.value = null;
	}

	protected UUID value() {

		return value;
	}
}

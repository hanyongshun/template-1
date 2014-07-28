package it.ms.template.template_parent.ddd.model;

import it.ms.template.template_parent.ddd.event.DomainEvent;
import it.ms.template.template_parent.ddd.event.DomainEventPublisherHolder;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class DomainObject implements Serializable {

	protected final <D extends DomainEvent> void publish(final D event) {

		DomainEventPublisherHolder.publish(event);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

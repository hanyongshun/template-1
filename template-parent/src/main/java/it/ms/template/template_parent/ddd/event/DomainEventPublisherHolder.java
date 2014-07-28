package it.ms.template.template_parent.ddd.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DomainEventPublisherHolder {

	private static final Logger logger = LoggerFactory.getLogger(DomainEventPublisherHolder.class);

	private static DomainEventPublisher domainEventPublisherInstance;

	@Autowired
	public DomainEventPublisherHolder(final DomainEventPublisher domainEventPublisher) {
		domainEventPublisherInstance = domainEventPublisher;
	}

	public static <D extends DomainEvent> void publish(final D event) {

		if (event == null) {
			throw new IllegalArgumentException("Event cannot be null");
		}
		if (domainEventPublisherInstance != null) {
			domainEventPublisherInstance.publish(event);
		} else {
			logger.warn("domainEventPublisherInstance is null! Cannot send event!");
		}
	}

}

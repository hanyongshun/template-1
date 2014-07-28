package it.ms.template.template_parent.ddd.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggingDomainEventPublisher implements DomainEventPublisher {

	private static final Logger logger = LoggerFactory.getLogger(LoggingDomainEventPublisher.class);

	@Override
	public <T extends DomainEvent> void publish(T event) {
		logger.info("Event was published {}", event.toString());
	}
}

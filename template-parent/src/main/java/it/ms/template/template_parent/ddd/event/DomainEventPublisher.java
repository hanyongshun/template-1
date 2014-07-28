package it.ms.template.template_parent.ddd.event;

public interface DomainEventPublisher {

	<T extends DomainEvent> void publish(T event);
}

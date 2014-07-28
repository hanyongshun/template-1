package it.ms.template.template_parent.ddd.model;

public abstract class AggregateRoot<T extends ID> extends Entity<T> {

	protected AggregateRoot(final T id) {
		super(id);
	}
}

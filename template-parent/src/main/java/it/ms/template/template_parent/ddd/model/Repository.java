package it.ms.template.template_parent.ddd.model;

import java.util.Optional;

public interface Repository<K extends ID, V extends AggregateRoot<K>> {

	Optional<V> get(K id);
}

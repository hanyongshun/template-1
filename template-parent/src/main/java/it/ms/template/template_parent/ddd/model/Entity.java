package it.ms.template.template_parent.ddd.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.time.LocalDateTime;

public abstract class Entity<T extends ID> extends DomainObject {

	private T id;
	private LocalDateTime creationDate;

	protected Entity(final T id) {
		setID(id);
	}

	public final LocalDateTime getCreationDate() {
		return creationDate;
	}

	@Override
	public final boolean equals(final Object other) {

		if (this == other) {
			return true;
		}
		if (other == null || getClass() != other.getClass()) {
			return false;
		}
		Entity entity = (Entity) other;
		if (id != null ? !id.equals(entity.id) : entity.id != null) {
			return false;
		}
		return true;
	}

	@Override
	public final int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	public final T id() {
		return id;
	}

	private void setID(final T id) {

		checkNotNull(id);
		this.id = id;
		this.creationDate = LocalDateTime.now();
	}
}

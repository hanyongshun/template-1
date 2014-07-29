package it.ms.template.template_parent.conversion;

import org.springframework.util.Assert;

final class ConvertiblePair<S, T> {

	private final Class<S> sourceType;

    private final Class<T> targetType;

	public ConvertiblePair(Class<S> sourceType, Class<T> targetType) {

		Assert.notNull(sourceType, "Source type must not be null");
		Assert.notNull(targetType, "Target type must not be null");
		this.sourceType = sourceType;
		this.targetType = targetType;
	}

	public Class<S> getSourceType() {
		return this.sourceType;
	}

	public Class<T> getTargetType() {
		return this.targetType;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null || obj.getClass() != ConvertiblePair.class) {
			return false;
		}
		ConvertiblePair other = (ConvertiblePair) obj;
		return this.sourceType.equals(other.sourceType) && this.targetType.equals(other.targetType);
	}

	@Override
	public int hashCode() {
		return this.sourceType.hashCode() * 31 + this.targetType.hashCode();
	}

	@Override
	public String toString() {

		return sourceType.getName() + " -> " + targetType.getName();
	}
}
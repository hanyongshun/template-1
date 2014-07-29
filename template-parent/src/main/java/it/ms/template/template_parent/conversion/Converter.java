package it.ms.template.template_parent.conversion;

import org.springframework.core.GenericTypeResolver;

/**
 * A converter converts a source object of type S to a target of type T. Subclasses of this class are thread-safe and
 * can be shared.
 * 
 * @param <S>
 *            The source type
 * @param <T>
 *            The target type
 */
public abstract class Converter<S, T> {

	/**
     * Convert the source of type S to target type T.
	 * 
	 * @param source
	 *            the source object to convert, which must be an instance of S
	 * @return the converted object, which must be an instance of T
	 * @throws IllegalArgumentException
	 *             if the source could not be converted to the desired target type
	 */
	public abstract T convert(S source);

	public final ConvertiblePair<S, T> convertiblePair() {

		return getRequiredTypeInfo(this, Converter.class);
	}

	private <S, T> ConvertiblePair<S, T> getRequiredTypeInfo(final Object instance, final Class<?> genericIfc) {

		Class<?>[] args = GenericTypeResolver.resolveTypeArguments(instance.getClass(), genericIfc);
		return (args != null) ? new ConvertiblePair<>((Class<S>) args[0], (Class<T>) args[1]) : null;
	}
}
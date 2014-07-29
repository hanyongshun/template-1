package it.ms.template.template_parent.conversion;

public interface Converters {

	<S, T> void put(ConvertiblePair<S, T> pair, Converter<?, ?> converter);

	<S, T> Converter find(Class<S> sourceType, Class<T> targetType);
}

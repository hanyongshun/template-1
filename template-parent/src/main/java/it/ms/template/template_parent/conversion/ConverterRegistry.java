package it.ms.template.template_parent.conversion;

public interface ConverterRegistry {

	<S, T> void addConverter(Converter<S, T> converter);

	boolean canConvert(Class<?> sourceType, Class<?> targetType);

	<S, T> Converter getConverter(Class<S> sourceType, Class<T> targetType);
}
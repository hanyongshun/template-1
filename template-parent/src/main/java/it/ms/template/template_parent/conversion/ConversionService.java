package it.ms.template.template_parent.conversion;

public interface ConversionService {

	void addConverter(Converter<?, ?> converter);

	boolean canConvert(Class<?> sourceType, Class<?> targetType);

	<T> T convert(Object source, Class<T> targetType);
}
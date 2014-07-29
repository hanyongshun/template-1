package it.ms.template.template_parent.conversion;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("conversionService")
public final class ChainingConversionService implements ConversionService {

	private ConverterRegistry registry = new ChainingConverterRegistry();

	// implementing ConverterRegistry

	@Override
	public void addConverter(final Converter<?, ?> converter) {

		registry.addConverter(converter);
	}

	// implementing ConversionService

	@Override
	public boolean canConvert(final Class<?> sourceType, final Class<?> targetType) {

		return registry.canConvert(sourceType, targetType);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T convert(final Object source, final Class<T> targetType) {

		Assert.notNull(targetType, "The targetType to convert to cannot be null");
		Converter<Object, T> converter = registry.getConverter(source.getClass(), targetType);
		return converter.convert(source);
	}
}

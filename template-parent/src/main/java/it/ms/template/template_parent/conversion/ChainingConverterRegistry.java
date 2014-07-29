package it.ms.template.template_parent.conversion;

import org.springframework.util.Assert;

final class ChainingConverterRegistry implements ConverterRegistry {

	private final Converters converters = new GraphConverters();

	@Override
    public <S, T> void addConverter(final Converter<S, T> converter) {

		ConvertiblePair<S, T> pair = converter.convertiblePair();
		Assert.notNull(pair, "Unable to the determine sourceType <S> and targetType "
				+ "<T> which your Converter<S, T> converts between; declare these generic types.");
		converters.put(pair, converter);
	}

	@Override
	public boolean canConvert(final Class<?> sourceType, final Class<?> targetType) {

		Assert.notNull(targetType, "The targetType to convert to cannot be null");
		// allow call canConvert(null, class);
		if (sourceType == null) {
			return true;
		}
		Converter converter = getConverter(sourceType, targetType);
		return (converter != null);
	}

	@Override
	public <S, T> Converter getConverter(final Class<S> sourceType, final Class<T> targetType) {

		Converter converter = converters.find(sourceType, targetType);
		if (converter == null) {
			converter = getDefaultConverter(sourceType, targetType);
		}
		return converter;
	}

	private <S, T> Converter<S, T> getDefaultConverter(final Class<S> sourceType, final Class<T> targetType) {

		return targetType.isAssignableFrom(sourceType) ? NoOpConverter.<S, T> newInstance() : null;
	}
}
package it.ms.template.template_parent.conversion;

import java.util.LinkedHashMap;
import java.util.Map;

final class KeyValueConverters implements Converters {

	private final Map<ConvertiblePair<?, ?>, Converter> converters = new LinkedHashMap<>(36);

	@Override
    public <S, T> void put(final ConvertiblePair<S, T> pair, final Converter<?, ?> converter) {

		converters.put(pair, converter);
	}

	@Override
	public <S, T> Converter find(final Class<S> sourceType, final Class<T> targetType) {

		ConvertiblePair<S, T> pair = new ConvertiblePair<>(sourceType, targetType);
		return converters.get(pair);
	}
}
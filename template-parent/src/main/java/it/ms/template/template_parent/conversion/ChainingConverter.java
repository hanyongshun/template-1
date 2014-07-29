package it.ms.template.template_parent.conversion;

import java.util.LinkedList;
import java.util.List;

import org.springframework.util.Assert;

final class ChainingConverter<S, T> extends Converter<S, T> {

	private final List<Converter<Object, ?>> chain;

	ChainingConverter(final List<Converter<Object, ?>> chain, final Class<S> sourceType, final Class<T> targetType) {

		Assert.notNull(chain, "Converters chain cannot be null");
        Assert.notEmpty(chain, "Converters chain cannot be empty");
		Assert.isTrue(sourceType.equals(chain.get(0).convertiblePair().getSourceType()),
				"Converters chain must start with type " + convertiblePair().getSourceType());
		if (chain.size() > 1) {
			Assert.isTrue(targetType.equals(chain.get(chain.size() - 1).convertiblePair().getTargetType()),
					"Converters chain must end with type " + convertiblePair().getTargetType());
		}
		this.chain = new LinkedList<>(chain);
	}

	@Override
	public final T convert(final S source) {

		Object result = source;
		for (Converter<Object, ?> converter : chain) {
			result = converter.convert(result);
		}
		return (T) result;
	}
}
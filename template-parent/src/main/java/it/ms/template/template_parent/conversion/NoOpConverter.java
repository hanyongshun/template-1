package it.ms.template.template_parent.conversion;

final class NoOpConverter<S, T> extends Converter<S, T> {

	public static <S, T> NoOpConverter newInstance() {

		return new NoOpConverter<S, T>();
    }

	private NoOpConverter() {

		// prevents control over instantiation
	}

	@Override
	public String toString() {

		return "NoOpConverter";
	}

	@Override
	public T convert(final S source) {

		return (T) source;
	}
}
package it.ms.template.template_parent.conversion;

import java.util.List;

import org.jgrapht.DirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.SimpleDirectedGraph;

final class GraphConverters implements Converters {

	private final DirectedGraph<Class<?>, Converter<?, ?>> converters = new SimpleDirectedGraph<>(
			((Class<Converter<?, ?>>) (Class<?>) Converter.class));

	@Override
	public <S, T> void put(final ConvertiblePair<S, T> pair, final Converter<?, ?> converter) {

		if (!converters.containsVertex(pair.getSourceType())) {
			converters.addVertex(pair.getSourceType());
		}
		if (!converters.containsVertex(pair.getTargetType())) {
			converters.addVertex(pair.getTargetType());
		}
		converters.addEdge(pair.getSourceType(), pair.getTargetType(), converter);
	}

	@Override
	public <S, T> Converter find(final Class<S> sourceType, final Class<T> targetType) {

		DijkstraShortestPath<Class<?>, Converter<?, ?>> dijkstraAlgorithm = new DijkstraShortestPath<>(converters,
				sourceType, targetType);
		List<Converter<?, ?>> chain = dijkstraAlgorithm.getPathEdgeList();

		return !chain.isEmpty() ? new ChainingConverter(chain, sourceType, targetType) : null;
	}
}

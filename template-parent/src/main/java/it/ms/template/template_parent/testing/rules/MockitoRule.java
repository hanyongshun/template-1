package it.ms.template.template_parent.testing.rules;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public final class MockitoRule implements MethodRule {

	public Statement apply(final Statement base, final FrameworkMethod method, final Object target) {

		return new Statement() {

			@Override
			public void evaluate() throws Throwable {

				MockitoAnnotations.initMocks(target);
				Throwable throwable = null;
				try {
					base.evaluate();
				} catch (final Throwable t) {
					throwable = t;
					throw t;
				} finally {
					try {
						Mockito.validateMockitoUsage();
					} catch (final Throwable t) {
						if (throwable != null) {
							t.printStackTrace();
						} else {
							throw t;
						}
					}
				}
			}
		};
	}
}

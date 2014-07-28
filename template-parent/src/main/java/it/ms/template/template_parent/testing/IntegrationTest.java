package it.ms.template.template_parent.testing;

import it.ms.template.template_parent.testing.rules.SpringContextRule;

import org.junit.Ignore;
import org.junit.Rule;
import org.springframework.context.ConfigurableApplicationContext;

@Ignore
public abstract class IntegrationTest extends UnitTest {

	@Rule
	public SpringContextRule contextRule = new SpringContextRule(getConfiguration());

	protected abstract Class<?> getConfiguration();

	protected ConfigurableApplicationContext context() {

		return contextRule.context();
	}
}

package it.ms.template.template_parent.testing;

import it.ms.template.template_parent.testing.rules.MockitoRule;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.rules.MethodRule;

/**
 * It is annotated with @Ignore because we don't want it to run. It is juts a base class of all the other tests.
 */
@Ignore
public abstract class UnitTest {

	@Rule
	public MethodRule mockRule = new MockitoRule();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
}

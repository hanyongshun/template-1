package it.ms.template.template_parent.testing.rules;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class SpringContextRule implements MethodRule {

	private final String[] locations;

	private final Class<?> classes;

	public SpringContextRule(final String... locations) {

		if (locations == null) {
			throw new IllegalArgumentException("Locations cannot be null");
		}
		this.locations = locations;
		this.classes = null;
	}

	public SpringContextRule(final Class<?> classes) {

		if (classes == null) {
			throw new IllegalArgumentException("Classes cannot be null");
		}
		this.classes = classes;
		this.locations = null;
	}

	public Statement apply(final Statement base, final FrameworkMethod method, final Object target) {

		return new Statement() {

			@Override
			public void evaluate() throws Throwable {

				ConfigurableApplicationContext context = context();
				AutowireCapableBeanFactory beanFactory = context.getAutowireCapableBeanFactory();
				beanFactory.autowireBean(target);
				context.start();
				try {
					base.evaluate();
				} finally {
					context.close();
				}
			}
		};
	}

	public ConfigurableApplicationContext context() {

		if (classes != null) {
			return new AnnotationConfigApplicationContext(classes);
		} else {
			return new ClassPathXmlApplicationContext(locations);
		}
	}
}

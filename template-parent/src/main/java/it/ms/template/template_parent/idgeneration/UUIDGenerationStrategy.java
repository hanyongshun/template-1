package it.ms.template.template_parent.idgeneration;

import org.springframework.stereotype.Component;

@Component
public final class UUIDGenerationStrategy implements IDGenerationStrategy {

	@Override
	public String get() {

		return new com.eaio.uuid.UUID().toString();
	}
}

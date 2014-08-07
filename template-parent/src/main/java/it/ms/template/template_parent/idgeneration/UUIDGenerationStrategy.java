package it.ms.template.template_parent.idgeneration;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public final class UUIDGenerationStrategy implements IDGenerationStrategy {

	@Override
	public UUID get() {

		return UUID.fromString(new com.eaio.uuid.UUID().toString());
	}
}

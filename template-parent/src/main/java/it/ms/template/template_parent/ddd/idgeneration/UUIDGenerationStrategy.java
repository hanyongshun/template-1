package it.ms.template.template_parent.ddd.idgeneration;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public final class UUIDGenerationStrategy implements IDGenerationStrategy {

	@Override
	public String get() {

		return UUID.randomUUID().toString();
	}
}

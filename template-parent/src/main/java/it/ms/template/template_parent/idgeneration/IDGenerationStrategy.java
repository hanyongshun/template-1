package it.ms.template.template_parent.idgeneration;

import java.util.UUID;
import java.util.function.Supplier;

public interface IDGenerationStrategy extends Supplier<UUID> {

}

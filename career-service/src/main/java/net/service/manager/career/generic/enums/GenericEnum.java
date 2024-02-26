package net.service.manager.career.generic.enums;

import java.util.Optional;

public interface GenericEnum<E extends Enum<E> & GenericEnum<E>> {
    String getValue();

    Optional<E> toEnum(String value);
}

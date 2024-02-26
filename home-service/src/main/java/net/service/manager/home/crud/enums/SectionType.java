package net.service.manager.home.crud.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import net.service.manager.home.generic.enums.GenericEnum;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SectionType implements GenericEnum<SectionType> {
    ABOUT("Qui sommes nous ?"),
    CONTACT("Contactez nous"),
    CAREER("Carrieres"),
    OFFER("Services offerts"),
    PARTNER("Nos partenaires");

    private final String value;

    SectionType(String value) {
        this.value = value;
    }

    public static List<SectionType> valuesInList() {
        SectionType[] arr = SectionType.class.getEnumConstants();
        return arr == null ? Collections.emptyList() : Arrays.asList(arr);
    }

    @Override
    @JsonValue
    public String getValue() {
        return this.value;
    }

    @Override
    @JsonCreator
    public Optional<SectionType> toEnum(String label) {
        return Stream.of(SectionType.values()).filter(e -> e.getValue().equals(label)).findFirst();
    }
}

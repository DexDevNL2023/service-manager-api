package net.service.manager.career.rest.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import net.service.manager.career.generic.enums.GenericEnum;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PartnerType implements GenericEnum<PartnerType> {
    PUBLIC("institution public"),
    PRIVEE("institution privee"),
    PARTICULIER("particulier");

    private final String value;

    PartnerType(String value) {
        this.value = value;
    }

    public static List<PartnerType> valuesInList() {
        PartnerType[] arr = PartnerType.class.getEnumConstants();
        return arr == null ? Collections.emptyList() : Arrays.asList(arr);
    }

    @Override
    @JsonValue
    public String getValue() {
        return this.value;
    }

    @Override
    @JsonCreator
    public Optional<PartnerType> toEnum(String label) {
        return Stream.of(PartnerType.values()).filter(e -> e.getValue().equals(label)).findFirst();
    }
}

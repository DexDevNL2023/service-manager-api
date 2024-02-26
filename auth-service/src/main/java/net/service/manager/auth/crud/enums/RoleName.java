package net.service.manager.auth.crud.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import net.service.manager.auth.generic.enums.GenericEnum;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RoleName implements GenericEnum<RoleName> {
    PARTNER("partner"),
    CUSTOMER("customer"),
    EMPLOYEE("employee"),
    ADMIN("admin");

    private final String value;

    RoleName(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String getValue() {
        return this.value;
    }

    @Override
    @JsonCreator
    public Optional<RoleName> toEnum(String label) {
        return Stream.of(RoleName.values()).filter(e -> e.getValue().equals(label)).findFirst();
    }

    public static List<RoleName> valuesInList() {
        RoleName[] arr = RoleName.class.getEnumConstants();
        return arr == null ? Collections.emptyList() : Arrays.asList(arr);
    }
}

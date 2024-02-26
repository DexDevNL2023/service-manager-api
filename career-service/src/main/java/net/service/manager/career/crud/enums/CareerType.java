package net.service.manager.career.crud.enums;

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
public enum CareerType implements GenericEnum<CareerType> {
    CDI("CDI"),
    CDD("CDD"),
    STAGE("Stage professionnel");

    private final String value;

    CareerType(String value) {
        this.value = value;
    }

    public static List<CareerType> valuesInList() {
        CareerType[] arr = CareerType.class.getEnumConstants();
        return arr == null ? Collections.emptyList() : Arrays.asList(arr);
    }

    @Override
    @JsonValue
    public String getValue() {
        return this.value;
    }

    @Override
    @JsonCreator
    public Optional<CareerType> toEnum(String label) {
        return Stream.of(CareerType.values()).filter(e -> e.getValue().equals(label)).findFirst();
    }
}

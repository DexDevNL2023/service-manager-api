package net.service.manager.contact.crud.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import net.service.manager.contact.generic.enums.GenericEnum;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ContactType implements GenericEnum<ContactType> {
    EMAIL("Adresse mail"),
    FAX("Fax"),
    TELEPHONE("Telephone"),
    WHATSAPP("Whatsapp");

    private final String value;

    ContactType(String value) {
        this.value = value;
    }

    public static List<ContactType> valuesInList() {
        ContactType[] arr = ContactType.class.getEnumConstants();
        return arr == null ? Collections.emptyList() : Arrays.asList(arr);
    }

    @Override
    @JsonValue
    public String getValue() {
        return this.value;
    }

    @Override
    @JsonCreator
    public Optional<ContactType> toEnum(String label) {
        return Stream.of(ContactType.values()).filter(e -> e.getValue().equals(label)).findFirst();
    }
}

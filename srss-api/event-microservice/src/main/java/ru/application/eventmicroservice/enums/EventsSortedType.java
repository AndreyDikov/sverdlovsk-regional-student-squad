package ru.application.eventmicroservice.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EventsSortedType {

    // Сортировка по дате начала события
    START_DATE("start_at"),

    // Сортировка по дате окончания события
    END_DATE("end_at"),

    // Сортировка по количеству участников по возрастанию
    PARTICIPANTS_ASC("number_users ASC"),

    // Сортировка по количеству участников по убыванию
    PARTICIPANTS_DESC("number_users DESC"),

    // Сортировка по названию
    NAME("name");

    String value;
}

package ru.viatsuk.sockswarehouse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Color {
    RED("Красный"), GREEN("Зеленый"), YELLOW("Желтый"), BLUE("Синий");

    private final String color;
}

package ru.viatsuk.sockswarehouse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Size {
    M(25), L(27), XL(29), XXL(31);

    private final int size;
}

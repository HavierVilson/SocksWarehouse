package ru.viatsuk.sockswarehouse.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OperationType {
    ACCEPTANCE("приемка"), WRITE_OFF("списание"), DELIVERY("выдача");

    private final String translate;
}

package ru.viatsuk.sockswarehouse.model;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Socks {
    private Color color;
    private Size size;
    @Positive(message = "Не может быть отрицательным")
    @Min(value = 0, message = "Не может быть меньше 0")
    @Max(value = 100, message = "Не может быть больше 100")
    private int cottonPart;
    @Positive(message = "Не может быть отрицательным")
    @Min(value = 1, message = "Не может быть меньше 1")
    private int quantity;
}
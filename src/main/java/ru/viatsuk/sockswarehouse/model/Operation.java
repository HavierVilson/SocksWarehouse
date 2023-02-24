package ru.viatsuk.sockswarehouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Operation {

    @NotEmpty
    private String operationType;

    private LocalDateTime time;

    public Operation(String operationType) {
        this.operationType = operationType;
        this.time = LocalDateTime.now();
    }

}

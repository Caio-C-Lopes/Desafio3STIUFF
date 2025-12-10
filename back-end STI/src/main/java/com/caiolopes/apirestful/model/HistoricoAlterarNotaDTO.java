package com.caiolopes.apirestful.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@Getter
@Setter
//DTO para fazer alteração apenas da nota
public class HistoricoAlterarNotaDTO {
    @NotNull(message = "A nota não pode ser nula")
    @Min(value = 0, message = "A nota mínima é 0")
    @Max(value = 100, message = "A nota máxima é 100")
    private Double nota;

}

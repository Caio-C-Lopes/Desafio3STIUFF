package com.caiolopes.apirestful.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@Getter
@Setter
//DTO para fazer alteração apenas da carga horária
public class DisciplinaDTO {
    @NotNull(message = "A carga horária não pode ser nula")
    @Min(value = 30, message = "A carga horária mínima é 30")
    @Max(value = 300, message = "A carga horária máxima é 300")
    private int cargaHoraria;

}

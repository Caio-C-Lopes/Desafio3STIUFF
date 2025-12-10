package com.caiolopes.apirestful.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//DTO para cadastrar um historico
public class HistoricoEntradaDTO {
    private Long matriculaAluno;
    private String codigoDisciplina;
    private Long codigoCurso;
    @NotNull(message = "A nota não pode ser nula")
    @Min(value = 0, message = "A nota mínima é 0")
    @Max(value = 100, message = "A nota máxima é 100")
    private Double nota;
    private String anoSemestre;
}


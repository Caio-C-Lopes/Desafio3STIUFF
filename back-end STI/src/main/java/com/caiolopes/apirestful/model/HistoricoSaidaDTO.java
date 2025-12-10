package com.caiolopes.apirestful.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
//DTO que vai pro front
public class HistoricoSaidaDTO {
    private Long matricula;
    private String codDisciplina;
    private Long codCurso;
    private Double nota;
    private int cargaHoraria;
    private String anoSemestre;
}

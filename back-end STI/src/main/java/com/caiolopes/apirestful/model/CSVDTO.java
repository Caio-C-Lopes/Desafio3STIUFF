package com.caiolopes.apirestful.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
//DTO auxiliar para salvar os dados do CSV
public class CSVDTO {
    private Long matricula;
    private String codDisciplina;
    private Long codCurso;
    private Double nota;
    private int cargaHoraria;
    private String anoSemestre;
}

package com.caiolopes.apirestful.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "CR")
public class CR {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long matriculaAluno;
    private Double nota;

    public CR(Long matriculaAluno, Double nota) {
        this.matriculaAluno = matriculaAluno;
        this.nota = nota;
    }
}

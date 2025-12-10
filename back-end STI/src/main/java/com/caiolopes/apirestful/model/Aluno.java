package com.caiolopes.apirestful.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "alunos")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A matrícula não pode ser nula")
    @Min(value = 0L, message = "A matrícula não pode ser negativa")
    private Long matricula;

    public Aluno(Long matricula) {
        this.matricula = matricula;
    }
}
package com.caiolopes.apirestful.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "historicos")
@NoArgsConstructor
public class Historico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double nota;
    private String anoSemestre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Historico(Aluno aluno, Disciplina disciplina, Curso curso,
                     Double nota, String anoSemestre) {
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.curso = curso;
        this.nota = nota;
        this.anoSemestre = anoSemestre;
    }
}

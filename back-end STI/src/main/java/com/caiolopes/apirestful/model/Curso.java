package com.caiolopes.apirestful.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cursos")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O código não pode ser nulo")
    @Min(value = 0, message = "O código não pode ser negativo")
    private Long codigo;

    //private String status

    public Curso(Long codigo) {
        this.codigo = codigo;
    }
}
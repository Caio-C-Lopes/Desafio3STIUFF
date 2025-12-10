package com.caiolopes.apirestful.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "disciplinas")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O código não pode ser nulo")
    private String codigo; //possível verificação particionando o codigo e vendo se está em uma das possíveis (GAN, TCC, SEN, ...)

    @NotNull(message = "A carga horária não pode ser nula")
    @Min(value = 30, message = "A carga horária mínima é 30")
    @Max(value = 300, message = "A carga horária máxima é 300")
    private int cargaHoraria;

    //private String status

    public Disciplina(String codigo, int cargaHoraria) {
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
    }
}
package com.caiolopes.apirestful.service;

import com.caiolopes.apirestful.exception.EntidadeExistenteException;
import com.caiolopes.apirestful.exception.EntidadeNaoEncontradaException;
import com.caiolopes.apirestful.model.Aluno;
import com.caiolopes.apirestful.repository.AlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//Teste Unitário
class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

    private Aluno aluno;

    @BeforeEach
    //inicia o ambiente e injeta os mocks
    void Inicio() {
        MockitoAnnotations.openMocks(this);
        aluno = new Aluno(1L);
        aluno.setId(1L);
    }

    @Test
    void cadastrarAlunoQuandoAlunoNaoExisteDeveSalvar() { //simula um ambiente onde aquele aluno não existe no banco
        when(alunoRepository.existsByMatricula(aluno.getMatricula())).thenReturn(false); //simula que não existe no banco
        when(alunoRepository.save(aluno)).thenReturn(aluno); //simula o retorno do aluno

        Aluno resultado = alunoService.cadastrarAluno(aluno);
        System.out.println("Aluno cadastrado: " + resultado);
    }

    @Test
    void cadastrarAlunoQuandoAlunoExisteDeveLancarExcecao() { //simula um ambiente onde aquele aluno existe no banco
        when(alunoRepository.existsByMatricula(aluno.getMatricula())).thenReturn(true);

        EntidadeExistenteException excecao = assertThrows(
                EntidadeExistenteException.class,
                () -> alunoService.cadastrarAluno(aluno)
        );

        System.out.println("Aluno já cadastrado | Exceção: " + excecao.getMessage());
    }

    @Test
    void recuperarAlunoPorIdQuandoExisteDeveRetornarAluno() {
        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno)); //simula um retorno de findById ao banco (aluno)

        Aluno resultado = alunoService.recuperarAlunoPorId(1L);
        System.out.println("Aluno encontrado: " + resultado);
    }

    @Test
    void recuperarAlunoPorIdQuandoNaoExisteDeveLancarExcecao() { //simula um retorno vazio de findById ao banco (!aluno)
        when(alunoRepository.findById(1L)).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException excecao = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> alunoService.recuperarAlunoPorId(1L)
        );

        System.out.println("Aluno não encontrado | Exceção: " + excecao.getMessage());
    }

}

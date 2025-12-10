package com.caiolopes.apirestful.service;

import com.caiolopes.apirestful.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
//Teste de Integração (H2 em memória, banco vem vazio)
class  CRServiceTest {

    @Autowired
    private CRService crService;

    @Autowired
    private HistoricoService historicoService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private DisciplinaService disciplinaService;


    @Test
    void calcularCRDeUmAluno() {
        Aluno aluno = new Aluno(1L);
        aluno = alunoService.cadastrarAluno(aluno);

        Disciplina d1 = disciplinaService.cadastrarDisciplina(new Disciplina("D1", 60));
        Disciplina d2 = disciplinaService.cadastrarDisciplina(new Disciplina("D2", 60));
        Disciplina d3 = disciplinaService.cadastrarDisciplina(new Disciplina("D3", 30));
        Disciplina d4 = disciplinaService.cadastrarDisciplina(new Disciplina("D4", 60));

        Curso c1 = cursoService.cadastrarCurso(new Curso(1L));
        Curso c2 = cursoService.cadastrarCurso(new Curso(2L));

        //cria históricos (aluno 113 do notas.csv)
        historicoService.cadastrarHistorico(new HistoricoEntradaDTO(aluno.getMatricula(), "D1", c1.getCodigo(), 80.0, "20172"));
        historicoService.cadastrarHistorico(new HistoricoEntradaDTO(aluno.getMatricula(), "D2", c1.getCodigo(), 0.0, "20171"));
        historicoService.cadastrarHistorico(new HistoricoEntradaDTO(aluno.getMatricula(), "D3", c2.getCodigo(), 93.0, "20181"));
        historicoService.cadastrarHistorico(new HistoricoEntradaDTO(aluno.getMatricula(), "D4", c2.getCodigo(), 77.0, "20171"));

        //Calcula o CR
        Double cr = crService.calcularCRDeUmAluno(aluno.getId());

        /*
         CR esperado: (80*60 + 0*60 + 93*30 + 77*60) / 60 + 60 + 30 + 60  =  12.210 / 210  =~  58,1428...  =  /10 e truncado -> 5,8
        */

        /* Testando adicionando um histórico de 60 horas e nota 50
         CR esperado: (80*60 + 0*60 + 93*30 + 77*60 + 50*60) / 60 + 60 + 60 + 30 + 60  =  15.210 / 270  =~  56,3333...  =  /10 e truncado -> 5,6
        */

        // 6. Verifica se o CR está correto (truncado)
        assertEquals(5.8, cr); // tolerância para evitar problemas de ponto flutuante
    }
}

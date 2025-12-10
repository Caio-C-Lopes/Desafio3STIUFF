package com.caiolopes.apirestful.service;

import com.caiolopes.apirestful.exception.EntidadeNaoEncontradaException;
import com.caiolopes.apirestful.model.CR;
import com.caiolopes.apirestful.repository.CRRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CRService {
    private final CRRepository crRepository;

    public List<CR> recuperarCRS() {
        return crRepository.findAll();
    }

    public CR recuperarCRPorId(Long id) {
        return crRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "cr com id = " + id + " não encontrado."));
    }

    public void salvarCRS() {
        List<Object[]> consulta = crRepository.calcularCRPorAluno();

        for (Object[] cr_consultado : consulta) {
            Long matricula = ((Number) cr_consultado[1]).longValue();//extrai a matricula
            Double cr = ((Number) cr_consultado[2]).doubleValue();//extrai o cr
            crRepository.save(new CR(matricula, cr));
        }
    }

    public void atualizarCr(Long matricula, Long idDoAluno){
        Double novoCR = calcularCRDeUmAluno(idDoAluno);
        CR cr = crRepository.findByMatriculaAluno(matricula);
        if (cr == null) cr = new CR(matricula, novoCR); //se nao existir, cria
        else cr.setNota(novoCR); //se existir, atualiza
        crRepository.save(cr);
    }

    public Double calcularCRDeUmAluno(Long matricula) {
        Double cr = crRepository.calcularCrDeUmAluno(matricula);
        if (cr == null) {
            throw new EntidadeNaoEncontradaException("Aluno de matricula = " + matricula + " não encontrado ou sem histórico.");
        }
        return cr;
    }

    public List<Object[]> calcularMediaCRPorCurso() {
        return crRepository.calcularMediaCRPorCurso();
    }

}

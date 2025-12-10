package com.caiolopes.apirestful.service;

import com.caiolopes.apirestful.exception.EntidadeExistenteException;
import com.caiolopes.apirestful.exception.EntidadeNaoEncontradaException;
import com.caiolopes.apirestful.model.Aluno;
import com.caiolopes.apirestful.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public List<Aluno> recuperarAlunos() {
        return alunoRepository.findAll();
    }

    public Aluno recuperarAlunoPorMatricula(Long matricula) {
        return alunoRepository.findByMatricula(matricula)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Aluno não encontrado"));
    }

    public Aluno cadastrarAluno(Aluno aluno) {
        if(alunoRepository.existsByMatricula(aluno.getMatricula())){
            throw new EntidadeExistenteException("Aluno de matricula: " + aluno.getMatricula() + " já cadastrado");
        }
        return alunoRepository.save(aluno);
    }

    public Aluno recuperarAlunoPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Aluno de id = " + id + " não encontrado."));
    }

    public Aluno importarAluno(Long matricula) {
        return alunoRepository.findByMatricula(matricula)
                .orElseGet(() -> alunoRepository.save(new Aluno(matricula)));
    }

    public boolean verificarExistencia(Long matricula){
        return alunoRepository.existsByMatricula(matricula);
    }

}

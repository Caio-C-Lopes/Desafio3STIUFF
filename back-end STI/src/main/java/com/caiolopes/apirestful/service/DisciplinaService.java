package com.caiolopes.apirestful.service;

import com.caiolopes.apirestful.exception.EntidadeExistenteException;
import com.caiolopes.apirestful.exception.EntidadeNaoEncontradaException;
import com.caiolopes.apirestful.model.Aluno;
import com.caiolopes.apirestful.model.Disciplina;
import com.caiolopes.apirestful.model.Historico;
import com.caiolopes.apirestful.repository.DisciplinaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;

    public List<Disciplina> recuperarDisciplinas() {
        return disciplinaRepository.findAll();
    }

    public Disciplina cadastrarDisciplina(Disciplina disciplina) {
        if(disciplinaRepository.existsByCodigo(disciplina.getCodigo())){
            throw new EntidadeExistenteException("Disciplina de código: " + disciplina.getCodigo() + " já cadastrada");
        }
        return disciplinaRepository.save(disciplina);
    }

    public Disciplina recuperarDisciplinaPorCodigo(String codigo) {
        return disciplinaRepository.findByCodigo(codigo).orElse(null);
    }

    public Disciplina alterarCargaHorariaDaDisciplina(Long id, int cargaHoraria){
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Disciplina com id = " + id + " não encontrado."));
        disciplina.setCargaHoraria(cargaHoraria);
        return disciplinaRepository.save(disciplina);
    }

    public Disciplina recuperarDisciplinaPorId(Long id) {
        return disciplinaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Disciplina com id = " + id + " não encontrado."));
    }

    //mesma lógica do cadastrar só que não lança exceção
    public Disciplina importarDisciplina(String codigo, int cargaHoraria) {
        return disciplinaRepository.findByCodigo(codigo)
                .orElseGet(() -> disciplinaRepository.save(new Disciplina(codigo, cargaHoraria)));
    }

    public boolean verificarExistencia(String codigo){
        return disciplinaRepository.existsByCodigo(codigo);
    }

}

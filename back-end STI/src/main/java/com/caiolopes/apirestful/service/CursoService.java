package com.caiolopes.apirestful.service;

import com.caiolopes.apirestful.exception.EntidadeExistenteException;
import com.caiolopes.apirestful.exception.EntidadeNaoEncontradaException;
import com.caiolopes.apirestful.model.Curso;
import com.caiolopes.apirestful.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public List<Curso> recuperarCursos() {
        return cursoRepository.findAll();
    }

    public Curso cadastrarCurso(Curso curso) {
        if(cursoRepository.existsByCodigo(curso.getCodigo())){
            throw new EntidadeExistenteException("Curso de código: " + curso.getCodigo() + " já cadastrado");
        }
        return cursoRepository.save(curso);
    }

    public Curso recuperarCursoPorCodigo(Long codigo) {
        return cursoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Curso de código = " + codigo + " não encontrado."));
    }

    public Curso recuperarCursoPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Curso com id = " + id + " não encontrado."));
    }

    public Curso importarCurso(Long codigo) {
        return cursoRepository.findByCodigo(codigo)
                .orElseGet(() -> cursoRepository.save(new Curso(codigo)));
    }

    public boolean verificarExistencia(Long codigo){
        return cursoRepository.existsByCodigo(codigo);
    }

}

package com.caiolopes.apirestful.controller;

import com.caiolopes.apirestful.model.Curso;
import com.caiolopes.apirestful.service.CursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("cursos")
@CrossOrigin(origins = "http://localhost:5173")// http://localhost:8080/cursos
public class CursoController {
    private final CursoService cursoService;

    //http://localhost:8080/cursos
    @GetMapping
    public List<Curso> recuperarCursos() {
        return cursoService.recuperarCursos();
    }

    @PostMapping
    public Curso cadastrarAluno(@RequestBody @Valid Curso curso) {
        return cursoService.cadastrarCurso(curso);
    }

    //http://localhost:8080/cursos/1
    @GetMapping("{idCurso}")
    public ResponseEntity<Curso> recuperarCursoPorId(@PathVariable("idCurso") Long id) {
        Curso aluno = cursoService.recuperarCursoPorId(id);
        return ResponseEntity.ok(aluno);
    }
}

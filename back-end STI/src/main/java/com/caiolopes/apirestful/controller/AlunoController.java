package com.caiolopes.apirestful.controller;

import com.caiolopes.apirestful.model.Aluno;
import com.caiolopes.apirestful.service.AlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("alunos")
@CrossOrigin(origins = "http://localhost:5173") // http://localhost:8080/alunos
public class AlunoController {

    private final AlunoService alunoService;

    //http://localhost:8080/alunos
    @GetMapping
    public List<Aluno> recuperarAlunos() {
        return alunoService.recuperarAlunos();
    }

    //http://localhost:8080/alunos
    @PostMapping
    public Aluno cadastrarAluno(@RequestBody @Valid Aluno aluno) {
        return alunoService.cadastrarAluno(aluno);
    }

    //http://localhost:8080/alunos/1
    @GetMapping("{idAluno}")
    public ResponseEntity<Aluno> recuperarAlunoPorId(@PathVariable("idAluno") Long id) {
        Aluno aluno = alunoService.recuperarAlunoPorId(id);
        return ResponseEntity.ok(aluno);
    }
}












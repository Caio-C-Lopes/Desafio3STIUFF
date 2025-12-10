package com.caiolopes.apirestful.controller;

import com.caiolopes.apirestful.model.Disciplina;
import com.caiolopes.apirestful.model.DisciplinaDTO;
import com.caiolopes.apirestful.service.DisciplinaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("disciplinas")
@CrossOrigin(origins = "http://localhost:5173") // http://localhost:8080/disciplinas
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    //http://localhost:8080/disciplinas
    @GetMapping
    public List<Disciplina> recuperarDisciplinas() {
        return disciplinaService.recuperarDisciplinas();
    }

    @PostMapping
    public Disciplina cadastrarDisciplina(@RequestBody @Valid Disciplina disciplina) {
        return disciplinaService.cadastrarDisciplina(disciplina);
    }

    @PutMapping("/{id}/carga-horaria")
    public Disciplina alterarCargaHorariaDaDisciplina(@PathVariable Long id, @RequestBody DisciplinaDTO dto){
        return disciplinaService.alterarCargaHorariaDaDisciplina(id, dto.getCargaHoraria());
    }

    //http://localhost:8080/disciplinas/1
    @GetMapping("{idDisciplina}")
    public ResponseEntity<Disciplina> recuperarDisciplinaPorId(@PathVariable("idDisciplina") Long id) {
        Disciplina disciplina = disciplinaService.recuperarDisciplinaPorId(id);
        return ResponseEntity.ok(disciplina);
    }

}

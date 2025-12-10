package com.caiolopes.apirestful.controller;

import com.caiolopes.apirestful.model.CR;
import com.caiolopes.apirestful.service.CRService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("crs")
@CrossOrigin(origins = "http://localhost:5173")// http://localhost:8080/crs
public class CRController {
    private final CRService crService;

    //http://localhost:8080/crs
    @GetMapping
    public List<CR> recuperarCRS() {
        return crService.recuperarCRS();
    }

    //http://localhost:8080/crs/1
    @GetMapping("{idCR}")
    public ResponseEntity<CR> recuperarCRPorId(@PathVariable("idCR") Long id) {
        CR cr = crService.recuperarCRPorId(id);
        return ResponseEntity.ok(cr);
    }

    //http://localhost:8080/crs/idDeUmAluno/1
    @GetMapping("/idDeUmAluno/{idCR}")
    public ResponseEntity<Double> calcularCRDeUmAluno(@PathVariable("idCR") Long id) {
        Double cr = crService.calcularCRDeUmAluno(id);
        return ResponseEntity.ok(cr);
    }

    //http://localhost:8080/crs/media-por-curso
    @GetMapping("/media-por-curso")
    public List<Object[]> mediaPorCurso() {
        return crService.calcularMediaCRPorCurso();
    }

}

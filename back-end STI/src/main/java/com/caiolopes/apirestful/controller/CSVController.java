package com.caiolopes.apirestful.controller;

import com.caiolopes.apirestful.model.CR;
import com.caiolopes.apirestful.service.CRService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/csv")
@CrossOrigin(origins = "http://localhost:5173")
public class CSVController {

    private final CRService crService;

    //csv dos crs por aluno
    @GetMapping("/crs")
    public ResponseEntity<byte[]> gerarCSVCRs() {
        List<CR> crs = crService.recuperarCRS();
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append("Matricula, CR\n");

        for (CR cr : crs) csvBuilder.append(cr.getMatriculaAluno()).append(",").append(cr.getNota()).append("\n");
        byte[] csvBytes = csvBuilder.toString().getBytes(StandardCharsets.UTF_8);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"crs.csv\"")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(csvBytes);
    }

    //csv dos crs médio por curso
    @GetMapping("/media-por-curso")
    public ResponseEntity<byte[]> gerarCSVMediaPorCurso() {
        List<Object[]> medias = crService.calcularMediaCRPorCurso();
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append("Curso, Cr medio\n");

        for (Object[] media : medias) csvBuilder.append(media[0]).append(",").append(media[1]).append("\n");
        byte[] csvBytes = csvBuilder.toString().getBytes(StandardCharsets.UTF_8);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"media_cr_por_curso.csv\"")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(csvBytes);
    }
}

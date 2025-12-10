package com.caiolopes.apirestful.controller;

import com.caiolopes.apirestful.model.Historico;
import com.caiolopes.apirestful.model.HistoricoAlterarNotaDTO;
import com.caiolopes.apirestful.model.HistoricoEntradaDTO;
import com.caiolopes.apirestful.model.HistoricoSaidaDTO;
import com.caiolopes.apirestful.service.HistoricoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("historicos")
@CrossOrigin(origins = "http://localhost:5173") // http://localhost:8080/historicos
public class HistoricoController {
    private final HistoricoService historicoService;

    //http://localhost:8080/historicos
    @GetMapping
    public List<HistoricoSaidaDTO> recuperarHistoricos() {
        return historicoService.recuperarHistoricos();
    }

    //tive que colocar pra dto, pra poder colocar a entrada que eu queria no front
    @PostMapping
    public Historico cadastrarHistorico(@RequestBody @Valid HistoricoEntradaDTO dto) {
        return historicoService.cadastrarHistorico(dto);
    }

    @GetMapping("/aluno/{matricula}")
    public List<HistoricoSaidaDTO> recuperarHistoricosPorMatricula(@PathVariable Long matricula) {
        return historicoService.recuperarHistoricosPorMatricula(matricula);
    }

}

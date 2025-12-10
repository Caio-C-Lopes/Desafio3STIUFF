package com.caiolopes.apirestful.util.leitura;

import com.caiolopes.apirestful.model.CSVDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class LeitorDeCSV {

    public List<CSVDTO> lerHistoricos(String arquivo) {
        List<CSVDTO> historicos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) { //fecha automaticamente ao EOF
            String line = br.readLine(); // cabeçalho
            line = br.readLine(); // pula cabeçalho

            while (line != null) {
                String[] campos = line.split(",");

                CSVDTO dto = new CSVDTO(
                        Long.parseLong(campos[0]), // matricula
                        campos[1], // codDisciplina
                        Long.parseLong(campos[2]), // codCurso
                        Double.parseDouble(campos[3]), // nota
                        Integer.parseInt(campos[4]), // cargaHoraria
                        campos[5] // anoSemestre
                );
                historicos.add(dto);
                line = br.readLine();
            }

        } catch (IOException e) {
            log.error("erro ao ler CSV: {}", e.getMessage());
        }

        return historicos;
    }
}

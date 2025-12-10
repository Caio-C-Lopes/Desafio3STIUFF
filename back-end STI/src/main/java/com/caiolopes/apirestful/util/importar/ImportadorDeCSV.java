package com.caiolopes.apirestful.util.importar;

import com.caiolopes.apirestful.model.CSVDTO;
import com.caiolopes.apirestful.util.leitura.LeitorDeCSV;
import com.caiolopes.apirestful.util.salvar.SalvadorDeDadosCSV;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ImportadorDeCSV {

    private final LeitorDeCSV leitorDeCSV;
    private final SalvadorDeDadosCSV salvadorDeDadosCSV;

    public void importarCSV(String csv) {
        List<CSVDTO> historicosCSVDTO = leitorDeCSV.lerHistoricos(csv); //lê o CSV e transforma em CSVDTOs
        salvadorDeDadosCSV.salvarHistoricos(historicosCSVDTO); //salva os CSVDTOs
    }
}

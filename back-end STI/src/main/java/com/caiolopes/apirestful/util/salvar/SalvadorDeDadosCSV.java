package com.caiolopes.apirestful.util.salvar;

import com.caiolopes.apirestful.model.*;
import com.caiolopes.apirestful.service.*;
import com.caiolopes.apirestful.util.facade.CSVFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalvadorDeDadosCSV {

    private final CSVFacade csvFacade;

    public void salvarHistoricos(List<CSVDTO> historicosDTO) {
        csvFacade.salvarHistoricos(historicosDTO);
    }

}


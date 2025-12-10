package com.caiolopes.apirestful.util.facade;

import com.caiolopes.apirestful.model.CSVDTO;
import com.caiolopes.apirestful.model.Aluno;
import com.caiolopes.apirestful.model.Disciplina;
import com.caiolopes.apirestful.model.Curso;
import com.caiolopes.apirestful.model.Historico;
import com.caiolopes.apirestful.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/*antes estava em SalvadorDeDadosCSV, com esse facade a gente
consegue diminuir o acoplamento e agora todas as nossas classes relacionadas ao CSV
dependem apenas da CSVFacade
 */

@Service
@RequiredArgsConstructor
public class CSVFacade {

    private final AlunoService alunoService;
    private final DisciplinaService disciplinaService;
    private final CursoService cursoService;
    private final HistoricoService historicoService;
    private final CRService crService;

    public void salvarHistoricos(List<CSVDTO> historicosDTO) {
        for (CSVDTO dto : historicosDTO) {
            /*para evitar duplicatas, eu sempre verifico se aquele objeto já existe
            tentando recuperar ele no banco, se não existir, eu crio um
             */

            //Busca ou Cria -> estava com problema de coesão Alternada, então criei um método importar para evitar isso
            Aluno aluno = alunoService.importarAluno(dto.getMatricula());
            Disciplina disciplina = disciplinaService.importarDisciplina(dto.getCodDisciplina(), dto.getCargaHoraria());
            Curso curso = cursoService.importarCurso(dto.getCodCurso());

            //historico (finalmente)
            Historico historico = new Historico(
                    aluno,
                    disciplina,
                    curso,
                    dto.getNota(),
                    dto.getAnoSemestre()
            );
            historicoService.cadastrarSeNaoExistir(historico);
        }

        //gera os crs
        crService.salvarCRS();
    }
}

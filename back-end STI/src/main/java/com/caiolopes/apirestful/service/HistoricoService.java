package com.caiolopes.apirestful.service;

import com.caiolopes.apirestful.exception.EntidadeNaoEncontradaException;
import com.caiolopes.apirestful.model.Historico;
import com.caiolopes.apirestful.model.HistoricoEntradaDTO;
import com.caiolopes.apirestful.model.HistoricoSaidaDTO;
import com.caiolopes.apirestful.repository.HistoricoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HistoricoService {

    private final HistoricoRepository historicoRepository;

    //Acoplamento necessário, nesse caso
    private final AlunoService alunoService;
    private final DisciplinaService disciplinaService;
    private final CursoService cursoService;
    private final CRService cRService;

    public List<HistoricoSaidaDTO> recuperarHistoricos() {
        return historicoRepository.findAllHistoricoDTO();
    }


    public Historico cadastrarHistorico(HistoricoEntradaDTO dto) {
        validarEntrada(dto);

        Historico historico = new Historico(
                alunoService.recuperarAlunoPorMatricula(dto.getMatriculaAluno()),
                disciplinaService.recuperarDisciplinaPorCodigo(dto.getCodigoDisciplina()),
                cursoService.recuperarCursoPorCodigo(dto.getCodigoCurso()),
                dto.getNota(),
                dto.getAnoSemestre()
        );

        //primeiro salva o historico
        historicoRepository.save(historico);

        //e entao atualiza o cr
        cRService.atualizarCr(dto.getMatriculaAluno(), historico.getAluno().getId());

        return historico;

    }

    private void validarEntrada(HistoricoEntradaDTO dto) {
        if (!alunoService.verificarExistencia(dto.getMatriculaAluno())) {
            throw new EntidadeNaoEncontradaException(
                    "Aluno de matrícula " + dto.getMatriculaAluno() + " não encontrado."
            );
        }

        if (!disciplinaService.verificarExistencia(dto.getCodigoDisciplina())) {
            throw new EntidadeNaoEncontradaException(
                    "Disciplina de código " + dto.getCodigoDisciplina() + " não encontrada."
            );
        }

        if (!cursoService.verificarExistencia(dto.getCodigoCurso())) {
            throw new EntidadeNaoEncontradaException(
                    "Curso de código " + dto.getCodigoCurso() + " não encontrado."
            );
        }
    }

    public List<HistoricoSaidaDTO> recuperarHistoricosPorMatricula(Long matricula) {
        List<HistoricoSaidaDTO> lista =
                historicoRepository.findHistoricosDTOByMatricula(matricula);

        if (lista.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Histórico não encontrado.");
        }

        return lista;
    }

    public void cadastrarSeNaoExistir(Historico historico) {
        boolean existe = historicoRepository.existsByAlunoIdAndDisciplinaIdAndCursoIdAndAnoSemestre(
                historico.getAluno().getId(),
                historico.getDisciplina().getId(),
                historico.getCurso().getId(),
                historico.getAnoSemestre()
        );

        if (!existe) {
            historicoRepository.save(historico);
        }
    }

}

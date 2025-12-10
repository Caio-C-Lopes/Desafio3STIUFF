package com.caiolopes.apirestful.repository;

import com.caiolopes.apirestful.model.Historico;
import com.caiolopes.apirestful.model.HistoricoSaidaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {

    boolean existsByAlunoIdAndDisciplinaIdAndCursoIdAndAnoSemestre(
            Long alunoId, Long disciplinaId, Long cursoId, String anoSemestre
    );

    @Query("""
    SELECT new com.caiolopes.apirestful.model.HistoricoSaidaDTO(
        a.matricula,
        d.codigo,
        c.codigo,
        h.nota,
        d.cargaHoraria,
        h.anoSemestre
    )
        FROM Historico h
        JOIN h.aluno a
        JOIN h.disciplina d
        JOIN h.curso c
""")
    List<HistoricoSaidaDTO> findAllHistoricoDTO();

    @Query("""
    SELECT new com.caiolopes.apirestful.model.HistoricoSaidaDTO(
        a.matricula,
        d.codigo,
        c.codigo,
        h.nota,
        d.cargaHoraria,
        h.anoSemestre
    )
        FROM Historico h
        JOIN h.aluno a
        JOIN h.disciplina d
        JOIN h.curso c
        WHERE a.matricula = :matricula
""")
    List<HistoricoSaidaDTO> findHistoricosDTOByMatricula(@Param("matricula") Long matricula);

}

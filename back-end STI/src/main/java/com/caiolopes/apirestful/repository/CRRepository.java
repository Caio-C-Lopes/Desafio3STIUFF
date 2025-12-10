package com.caiolopes.apirestful.repository;

import com.caiolopes.apirestful.model.CR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
//retorna um array de objetos devido à native query
public interface CRRepository extends JpaRepository<CR, Long> {
    //recupera o cr de um aluno
    @Query(value = """
    SELECT TRUNCATE((SUM(h.nota * d.carga_horaria) / SUM(d.carga_horaria)) / 10, 1) AS cr FROM historicos h
    JOIN disciplinas d ON d.id = h.disciplina_id
    WHERE h.aluno_id = :matriculaAluno
""", nativeQuery = true)
    Double calcularCrDeUmAluno(@Param("matriculaAluno") Long matriculaAluno);

    //recupera o cr de todos os alunos
    @Query(value = """
    SELECT h.aluno_id, a.matricula, TRUNCATE((SUM(h.nota * d.carga_horaria) / SUM(d.carga_horaria)) / 10, 1) AS cr FROM historicos h
    JOIN alunos a ON a.id = h.aluno_id
    JOIN disciplinas d ON d.id = h.disciplina_id
    GROUP BY h.aluno_id, a.matricula
    ORDER BY a.matricula
""", nativeQuery = true)
    List<Object[]> calcularCRPorAluno();

    //recupera a media de cr por curso
    //calcula o cr de cada aluno dentro daquele curso e depois faz a média
    @Query(value = """
    SELECT c.codigo, ROUND(AVG(cr_aluno), 1) AS media_cr
    FROM (
        SELECT h.curso_id, h.aluno_id, (SUM(h.nota * d.carga_horaria) / SUM(d.carga_horaria)) / 10 AS cr_aluno
        FROM historicos h
        JOIN disciplinas d ON d.id = h.disciplina_id
        GROUP BY h.curso_id, h.aluno_id
    ) crs_alunos
    JOIN cursos c ON c.id = crs_alunos.curso_id
    GROUP BY c.codigo
    ORDER BY c.codigo
""", nativeQuery = true)
    List<Object[]> calcularMediaCRPorCurso();

    CR findByMatriculaAluno(Long matricula);
}

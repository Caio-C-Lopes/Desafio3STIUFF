package com.caiolopes.apirestful.repository;

import com.caiolopes.apirestful.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Optional<Aluno> findByMatricula(Long matricula);
    boolean existsByMatricula(Long Matricula);
}
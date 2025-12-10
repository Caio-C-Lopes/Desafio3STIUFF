package com.caiolopes.apirestful.repository;

import com.caiolopes.apirestful.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByCodigo(Long codigo);
    boolean existsByCodigo(Long codigo);
}
package br.com.teste.fipecafi.repository;

import br.com.teste.fipecafi.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    List<Turma> findByCursoId(Long cursoId); // Método para buscar turmas pelo ID do curso
}
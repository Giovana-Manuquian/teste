package br.com.teste.fipecafi.repository;

import br.com.teste.fipecafi.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    // Método para buscar turmas associadas a um curso específico
    List<Turma> findByCursoId(Long cursoId);
}

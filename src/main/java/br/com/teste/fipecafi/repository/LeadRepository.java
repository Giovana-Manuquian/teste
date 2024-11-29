package br.com.teste.fipecafi.repository;

import br.com.teste.fipecafi.entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LeadRepository extends JpaRepository<Lead, Long> {

    // Método para buscar leads filtrados por nome, email e curso
    List<Lead> findByNomeContainingAndEmailContainingAndCursoId(String nome, String email, Long curso);

    // Método para buscar leads filtrados apenas por nome e email
    List<Lead> findByNomeContainingAndEmailContaining(String nome, String email);
}

package br.com.teste.fipecafi.repository;

import br.com.teste.fipecafi.entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LeadRepository extends JpaRepository<Lead, Long> {

    // Busca filtrada por nome, email e ID do curso
    List<Lead> findByNomeContainingAndEmailContainingAndCursoId(String nome, String email, Long curso);

    // Busca filtrada apenas por nome e email
    List<Lead> findByNomeContainingAndEmailContaining(String nome, String email);
}

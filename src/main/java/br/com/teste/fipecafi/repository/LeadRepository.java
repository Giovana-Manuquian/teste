package br.com.teste.fipecafi.repository;

import br.com.teste.fipecafi.entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadRepository extends JpaRepository<Lead, Long> {
}

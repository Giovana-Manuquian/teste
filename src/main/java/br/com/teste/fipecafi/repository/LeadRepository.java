package br.com.teste.fipecafi.repository;

import br.com.teste.fipecafi.entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {
    // Não é necessário implementar nada aqui para o filtro, já que estamos usando a lógica no serviço
}

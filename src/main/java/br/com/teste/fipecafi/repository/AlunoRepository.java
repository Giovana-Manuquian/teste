package br.com.teste.fipecafi.repository;

import br.com.teste.fipecafi.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
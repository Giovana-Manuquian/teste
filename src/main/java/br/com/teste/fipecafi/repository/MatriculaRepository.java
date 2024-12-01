package br.com.teste.fipecafi.repository;

import br.com.teste.fipecafi.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepository extends JpaRepository<Aluno, Long> {

}
package br.com.teste.fipecafi.repository;

import br.com.teste.fipecafi.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    // Não é necessário adicionar métodos personalizados, o JpaRepository já oferece os métodos básicos
}
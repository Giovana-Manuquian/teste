package br.com.teste.fipecafi.service;

import br.com.teste.fipecafi.entity.Curso;
import br.com.teste.fipecafi.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> listarCursos() {
        return cursoRepository.findAll();  // Retorna todos os cursos do banco de dados
    }
}

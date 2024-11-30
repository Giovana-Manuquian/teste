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

    // Buscar todos os cursos no banco de dados
    public List<Curso> listarCursos() {
        return cursoRepository.findAll(); // Busca no banco
    }

    // Salvar uma lista de cursos no banco de dados
    public List<Curso> salvarCursos(List<Curso> cursos) {
        return cursoRepository.saveAll(cursos); // Salva no banco
    }
}

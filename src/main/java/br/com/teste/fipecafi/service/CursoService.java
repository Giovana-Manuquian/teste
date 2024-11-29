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

    // Método para listar todos os cursos
    public List<Curso> listarCursos() {
        return cursoRepository.findAll();  // Retorna todos os cursos do banco de dados
    }

    // Método para salvar um único curso
    public Curso salvarCurso(Curso curso) {
        return cursoRepository.save(curso);  // Salva o curso no banco de dados
    }

    // Método para salvar múltiplos cursos
    public List<Curso> salvarCursos(List<Curso> cursos) {
        return cursoRepository.saveAll(cursos);  // Salva todos os cursos no banco de dados
    }
}

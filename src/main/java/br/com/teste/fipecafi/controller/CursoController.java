package br.com.teste.fipecafi.controller;

import br.com.teste.fipecafi.entity.Curso;  // Importar a entidade Curso
import br.com.teste.fipecafi.entity.Turma;  // Importar a entidade Turma
import br.com.teste.fipecafi.service.CursoService;  // Importar o serviço CursoService
import br.com.teste.fipecafi.repository.TurmaRepository;  // Importar o repositório de Turmas
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")  // Definir a URL base para o controlador
public class CursoController {

    @Autowired
    private CursoService cursoService;  // Serviço responsável pela lógica de cursos

    @Autowired
    private TurmaRepository turmaRepository; // Repositório de turmas

    // Método para listar todos os cursos
    @GetMapping
    public ResponseEntity<List<Curso>> getAllCursos() {
        try {
            List<Curso> cursos = cursoService.listarCursos();
            return ResponseEntity.ok(cursos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Método para listar turmas de um curso específico
    @GetMapping("/{cursoId}/turmas")  // Endpoint que recebe o ID do curso para buscar suas turmas
    public ResponseEntity<List<Turma>> getTurmasPorCurso(@PathVariable Long cursoId) {
        try {
            List<Turma> turmas = turmaRepository.findByCursoId(cursoId);  // Busca turmas associadas ao curso
            if (turmas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Caso não haja turmas
            }
            return ResponseEntity.ok(turmas);  // Retorna as turmas associadas ao curso
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);  // Em caso de erro
        }
    }

    // Método para criar um ou múltiplos cursos (modificado para aceitar uma lista)
    @PostMapping
    public ResponseEntity<List<Curso>> criarCursos(@RequestBody List<Curso> cursos) {
        try {
            List<Curso> novosCursos = cursoService.salvarCursos(cursos);  // Salva todos os cursos enviados
            return ResponseEntity.status(HttpStatus.CREATED).body(novosCursos);  // Retorna a lista de cursos criados
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);  // Caso haja erro
        }
    }
}

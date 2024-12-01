package br.com.teste.fipecafi.controller;

import br.com.teste.fipecafi.entity.Turma; // Presumindo que a entidade se chama Turma
import br.com.teste.fipecafi.repository.TurmaRepository; // Repositório para a entidade Turma
import br.com.teste.fipecafi.repository.CursoRepository; // Repositório para a entidade Curso
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Classe para representar a resposta de erro
class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

@RestController
@RequestMapping("/api/turmas")  // Definindo a URL base para o controlador de turmas
public class TurmaController {

    @Autowired
    private TurmaRepository turmaRepository; // Repositório de turmas

    @Autowired
    private CursoRepository cursoRepository; // Repositório de cursos

    // Método para listar todas as turmas
    @GetMapping
    public ResponseEntity<List<Turma>> getAllTurmas() {
        try {
            List<Turma> turmas = turmaRepository.findAll(); // Busca todas as turmas
            if (turmas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Caso não haja turmas
            }
            return ResponseEntity.ok(turmas); // Retorna todas as turmas
        } catch (Exception e) {
            e.printStackTrace(); // Imprime a stack trace para depuração
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Em caso de erro
        }
    }

    // Método para listar turmas de um curso específico
    @GetMapping("/curso/{cursoId}/turmas")  // Endpoint que recebe o ID do curso para buscar suas turmas
    public ResponseEntity<?> getTurmasPorCurso(@PathVariable Long cursoId) {
        try {
            List<Turma> turmas = turmaRepository.findByCursoId(cursoId);  // Busca turmas associadas ao curso
            if (turmas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Nenhuma turma encontrada para este curso."));
            }
            return ResponseEntity.ok(turmas);  // Retorna as turmas associadas ao curso
        } catch (Exception e) {
            e.printStackTrace(); // Imprime a stack trace para depuração
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Erro interno do servidor."));  // Em caso de erro
        }
    }

    // Método para criar uma nova turma
    @PostMapping
    public ResponseEntity<?> criarTurma(@RequestBody Turma turma) {
        try {
            // Verifique se o curso existe antes de associá-lo
            if (turma.getCurso() == null || turma.getCurso().getId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Curso não fornecido.")); // Retorna erro se o curso não for fornecido
            }

            // Verifique se o curso existe na tabela 'cursos'
            if (!cursoRepository.existsById(turma.getCurso().getId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Curso não encontrado.")); // Retorna erro se o curso não existir
            }

            // Salva a turma
            Turma turmaSalva = turmaRepository.save(turma);
            return ResponseEntity.status(HttpStatus.CREATED).body(turmaSalva); // Retorna a turma criada
        } catch (Exception e) {
            e.printStackTrace(); // Imprime a stack trace para depuração
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Erro interno ao criar a turma.")); // Retorna erro 500
        }
    }

    // Método para deletar uma turma
    @DeleteMapping("/{id}") // Endpoint para deletar uma turma pelo ID
    public ResponseEntity<?> deleteTurma(@PathVariable Long id) {
        try {
            if (!turmaRepository.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Turma não encontrada.")); // Retorna erro 404 se a turma não existir
            }

            turmaRepository.deleteById(id); // Deleta a turma pelo ID
            return ResponseEntity.noContent().build(); // Retorna 204 No Content se deletado com sucesso
        } catch (Exception e) {
            e.printStackTrace(); // Imprime a stack trace para depuração
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Erro interno ao deletar a turma.")); // Retorna erro 500
        }
    }
}
package br.com.teste.fipecafi.controller;

import br.com.teste.fipecafi.entity.Aluno;
import br.com.teste.fipecafi.entity.Curso;
import br.com.teste.fipecafi.entity.Turma;
import br.com.teste.fipecafi.service.MatriculaService;
import br.com.teste.fipecafi.service.CursoService;
import br.com.teste.fipecafi.service.TurmaService; // Importando o TurmaService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private CursoService cursoService; // Para buscar Curso

    @Autowired
    private TurmaService turmaService; // Para buscar Turma

    @PostMapping("/confirmar")
    public ResponseEntity<?> confirmarMatricula(@RequestBody Aluno aluno) {
        // Validações
        if (aluno == null || aluno.getCurso() == null || aluno.getTurma() == null) {
            return ResponseEntity.badRequest().body("Curso e Turma não podem ser nulos.");
        }

        //Busca os objetos Curso e Turma
        Curso curso = cursoService.findById(aluno.getCurso().getId());
        if (curso == null) {
            return ResponseEntity.badRequest().body("Curso não encontrado.");
        }

        Turma turma = turmaService.findById(aluno.getTurma().getId());
        if (turma == null) {
            return ResponseEntity.badRequest().body("Turma não encontrada.");
        }

        aluno.setCurso(curso);
        aluno.setTurma(turma);

        try {
            Aluno alunoMatriculado = matriculaService.matricularAluno(aluno);
            return ResponseEntity.ok(alunoMatriculado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao matricular aluno: " + e.getMessage());
        }
    }
}
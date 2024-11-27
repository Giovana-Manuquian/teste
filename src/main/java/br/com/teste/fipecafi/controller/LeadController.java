package br.com.teste.fipecafi.controller;

import br.com.teste.fipecafi.dto.MatriculaRequest;  // Importando a classe MatriculaRequest
import br.com.teste.fipecafi.entity.Aluno;
import br.com.teste.fipecafi.entity.Lead;
import br.com.teste.fipecafi.entity.Turma;
import br.com.teste.fipecafi.repository.AlunoRepository;
import br.com.teste.fipecafi.repository.LeadRepository;
import br.com.teste.fipecafi.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    // Método para listar todos os leads
    @GetMapping
    public ResponseEntity<Iterable<Lead>> getAllLeads() {
        try {
            Iterable<Lead> leads = leadRepository.findAll();
            return ResponseEntity.ok(leads);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Método para criar um novo lead
    @PostMapping
    public ResponseEntity<Lead> createLead(@RequestBody Lead lead) {
        Lead savedLead = leadRepository.save(lead);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLead);
    }

    // Método para matricular o aluno
    @PostMapping("/{leadId}/matricular")
    public ResponseEntity<Aluno> matricularAluno(@PathVariable Long leadId, @RequestBody MatriculaRequest matriculaRequest) {
        try {
            // Buscar o lead pelo ID
            Lead lead = leadRepository.findById(leadId)
                    .orElseThrow(() -> new RuntimeException("Lead não encontrado"));

            // Buscar a turma pelo ID fornecido no corpo da requisição
            Turma turma = turmaRepository.findById(matriculaRequest.getTurmaId())
                    .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

            // Criar o aluno a partir do lead
            Aluno aluno = new Aluno();
            aluno.setNome(lead.getNome());
            aluno.setTelefone(lead.getTelefone());
            aluno.setEmail(lead.getEmail());
            aluno.setCurso(lead.getCurso());  // Associa o curso do lead
            aluno.setTurma(turma);  // Associa a turma
            aluno.setCodigoMatricula(generateCodigoMatricula());  // Gerar um código de matrícula aleatório
            aluno.setDataCadastro("2024-11-26");  // Definir data de cadastro

            // Salvar o aluno no banco de dados
            Aluno savedAluno = alunoRepository.save(aluno);

            // Retornar o aluno criado com status 201 (Created)
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAluno);

        } catch (Exception e) {
            // Captura qualquer erro e retorna um erro genérico
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Método para listar todos os alunos matriculados
    @GetMapping("/alunos")
    public ResponseEntity<Iterable<Aluno>> getAllAlunos() {
        try {
            Iterable<Aluno> alunos = alunoRepository.findAll();
            return ResponseEntity.ok(alunos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Método para exibir detalhes de um aluno específico
    @GetMapping("/alunos/{id}")
    public ResponseEntity<Aluno> getAlunoById(@PathVariable Long id) {
        try {
            Aluno aluno = alunoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
            return ResponseEntity.ok(aluno);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Método auxiliar para gerar o código de matrícula
    private Integer generateCodigoMatricula() {
        return (int) (Math.random() * 10000);
    }
}
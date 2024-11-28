package br.com.teste.fipecafi.controller;

import br.com.teste.fipecafi.dto.MatriculaRequest;
import br.com.teste.fipecafi.entity.Aluno;
import br.com.teste.fipecafi.entity.Lead;
import br.com.teste.fipecafi.entity.Turma;
import br.com.teste.fipecafi.repository.AlunoRepository;
import br.com.teste.fipecafi.repository.TurmaRepository;
import br.com.teste.fipecafi.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    @Autowired
    private LeadService leadService;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    // Método para listar todos os leads
    @GetMapping
    public ResponseEntity<Iterable<Lead>> getAllLeads() {
        try {
            Iterable<Lead> leads = leadService.getAllLeads();
            return ResponseEntity.ok(leads);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Método para filtrar leads com base nos parâmetros fornecidos
    @GetMapping("/filter")
    public ResponseEntity<List<Lead>> filterLeads(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String curso) {
        try {
            List<Lead> filteredLeads = leadService.filterLeads(nome, email, curso);
            return ResponseEntity.ok(filteredLeads);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Método para criar um novo lead
    @PostMapping
    public ResponseEntity<Lead> createLead(@RequestBody Lead lead) {
        try {
            Lead savedLead = leadService.saveLead(lead);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedLead);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Método para atualizar um lead
    @PutMapping("/{leadId}")
    public ResponseEntity<Lead> updateLead(@PathVariable Long leadId, @RequestBody Lead updatedLead) {
        try {
            Lead lead = leadService.updateLead(leadId, updatedLead);  // Usando o serviço para atualizar
            return ResponseEntity.ok(lead);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Método para matricular o aluno
    @PostMapping("/{leadId}/matricular")
    public ResponseEntity<Aluno> matricularAluno(@PathVariable Long leadId, @RequestBody MatriculaRequest matriculaRequest) {
        try {
            Lead lead = leadService.getLeadById(leadId);
            Turma turma = turmaRepository.findById(matriculaRequest.getTurmaId())
                    .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

            Aluno aluno = new Aluno();
            aluno.setNome(lead.getNome());
            aluno.setTelefone(lead.getTelefone());
            aluno.setEmail(lead.getEmail());
            aluno.setCurso(lead.getCurso());
            aluno.setTurma(turma);
            aluno.setCodigoMatricula(generateCodigoMatricula());
            aluno.setDataCadastro("2024-11-26");

            Aluno savedAluno = alunoRepository.save(aluno);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAluno);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Método auxiliar para gerar o código de matrícula
    private Integer generateCodigoMatricula() {
        return (int) (Math.random() * 10000);
    }
}

package br.com.teste.fipecafi.controller;

import br.com.teste.fipecafi.entity.Lead;
import br.com.teste.fipecafi.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5501")  // Permite o frontend da URL especificada
@RestController
@RequestMapping("/api/leads")
public class LeadController {
    @Autowired
    private LeadService leadService;

    // Endpoint para filtrar os leads com base nos parâmetros fornecidos
    @GetMapping("/filter")
    public List<Lead> getFilteredLeads(@RequestParam(required = false) String nome,
                                       @RequestParam(required = false) String email,
                                       @RequestParam(required = false) String curso) {
        if (nome == null && email == null && curso == null) {
            return leadService.getAllLeads(); // Retorna todos os leads
        }
        return leadService.filterLeads(nome, email, curso);
    }

    // Endpoint para retornar todos os leads
    @GetMapping("/")
    public List<Lead> getAllLeads() {
        return leadService.getAllLeads();
    }

    // Endpoint para criar um novo lead
    @PostMapping("/")
    public ResponseEntity<Lead> criarLead(@RequestBody Lead lead) {
        Lead savedLead = leadService.saveLead(lead); // Salva o lead no banco de dados
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLead);  // Retorna o lead com status 201 Created
    }
}

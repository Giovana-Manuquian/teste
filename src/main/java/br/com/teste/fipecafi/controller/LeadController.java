package br.com.teste.fipecafi.controller;

import br.com.teste.fipecafi.entity.Lead;
import br.com.teste.fipecafi.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        // Se nenhum parâmetro for fornecido, retorna todos os leads
        if (nome == null && email == null && curso == null) {
            return leadService.getAllLeads();  // Método para retornar todos os leads
        }
        return leadService.filterLeads(nome, email, curso);
    }

    // Endpoint para retornar todos os leads (usado quando não há parâmetros de filtro)
    @GetMapping("/")
    public List<Lead> getAllLeads() {
        return leadService.getAllLeads();
    }
}

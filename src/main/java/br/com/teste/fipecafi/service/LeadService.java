package br.com.teste.fipecafi.service;

import br.com.teste.fipecafi.entity.Lead;
import br.com.teste.fipecafi.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeadService {

    @Autowired
    private LeadRepository leadRepository;

    // Método para retornar todos os leads
    public Iterable<Lead> getAllLeads() {
        return leadRepository.findAll();
    }

    // Método para salvar um lead
    public Lead saveLead(Lead lead) {
        return leadRepository.save(lead);
    }

    // Método para encontrar um lead por ID
    public Lead getLeadById(Long leadId) {
        return leadRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException("Lead não encontrado"));
    }

    // Método para atualizar um lead
    public Lead updateLead(Long leadId, Lead updatedLead) {
        return leadRepository.findById(leadId)
                .map(existingLead -> {
                    existingLead.setNome(updatedLead.getNome());
                    existingLead.setTelefone(updatedLead.getTelefone());
                    existingLead.setEmail(updatedLead.getEmail());
                    existingLead.setCurso(updatedLead.getCurso());
                    return leadRepository.save(existingLead);
                })
                .orElseThrow(() -> new RuntimeException("Lead não encontrado para atualização"));
    }

    // Método para filtrar leads com base em nome, email ou curso
    public List<Lead> filterLeads(String nome, String email, String curso) {
        return leadRepository.findAll().stream()
                .filter(lead -> (nome == null || lead.getNome() != null && lead.getNome().contains(nome)) &&
                        (email == null || lead.getEmail() != null && lead.getEmail().contains(email)) &&
                        (curso == null || (lead.getCurso() != null && lead.getCurso().getDescricao() != null && lead.getCurso().getDescricao().contains(curso))))
                .collect(Collectors.toList());
    }
}

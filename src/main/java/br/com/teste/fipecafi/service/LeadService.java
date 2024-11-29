package br.com.teste.fipecafi.service;

import br.com.teste.fipecafi.entity.Lead;
import br.com.teste.fipecafi.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadService {

    @Autowired
    private LeadRepository leadRepository;

    // Método para filtrar os leads
    public List<Lead> filterLeads(String nome, String email, String curso) {
        if (curso != null && !curso.isEmpty()) {
            try {
                Long cursoId = Long.valueOf(curso);  // Tentando converter o curso para Long
                return leadRepository.findByNomeContainingAndEmailContainingAndCursoId(nome, email, cursoId);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("O parâmetro 'curso' deve ser um número válido.");
            }
        } else {
            return leadRepository.findByNomeContainingAndEmailContaining(nome, email);
        }
    }

    // Método para retornar todos os leads
    public List<Lead> getAllLeads() {
        return leadRepository.findAll();  // Retorna todos os leads da base de dados
    }
}

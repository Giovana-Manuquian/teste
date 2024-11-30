package br.com.teste.fipecafi.service;

import br.com.teste.fipecafi.entity.Curso;
import br.com.teste.fipecafi.entity.Lead;
import br.com.teste.fipecafi.repository.CursoRepository;
import br.com.teste.fipecafi.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadService {

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private CursoRepository cursoRepository; // Adicione esta linha

    // Método para salvar um lead no banco de dados
    public Lead saveLead(Lead lead) {
        // Verifica se o curso existe
        if (lead.getCurso() != null && lead.getCurso().getId() != null) {
            Curso curso = cursoRepository.findById(lead.getCurso().getId())
                    .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
            lead.setCurso(curso); // Define o curso encontrado
        } else {
            throw new IllegalArgumentException("Curso deve ser fornecido e não pode ser nulo.");
        }
        return leadRepository.save(lead); // Salva o lead
    }

    // Método para filtrar os leads
    public List<Lead> filterLeads(String nome, String email, String curso) {
        if (curso != null && !curso.isEmpty()) {
            try {
                Long cursoId = Long.valueOf(curso); // Converte o curso para Long
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
        return leadRepository.findAll(); // Retorna todos os leads
    }
}
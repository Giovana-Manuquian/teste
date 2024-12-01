package br.com.teste.fipecafi.service;

import br.com.teste.fipecafi.entity.Turma;
import br.com.teste.fipecafi.repository.TurmaRepository; // Supondo que você tenha um repositório para Turma
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    // Buscar todos as turmas no banco de dados
    public List<Turma> listarTurmas() {
        return turmaRepository.findAll(); // Busca no banco
    }

    // Salvar uma lista de turmas no banco de dados
    public List<Turma> salvarTurmas(List<Turma> turmas) {
        return turmaRepository.saveAll(turmas); // Salva no banco
    }

    // Buscar uma turma pelo ID
    public Turma findById(Long id) {
        return turmaRepository.findById(id).orElse(null);
    }
}
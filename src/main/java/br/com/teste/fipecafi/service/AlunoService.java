package br.com.teste.fipecafi.service;

import br.com.teste.fipecafi.entity.Aluno;
import br.com.teste.fipecafi.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno criarAluno(Aluno aluno) {
        aluno.setDataCadastro(LocalDate.now().toString()); // Definindo a data de cadastro como a data atual
        return alunoRepository.save(aluno);
    }

    public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

    public Aluno buscarAlunoPorId(Long id) {
        return alunoRepository.findById(id).orElse(null);
    }

    public Aluno atualizarAluno(Long id, Aluno aluno) {
        aluno.setId(id);
        aluno.setDataCadastro(LocalDate.now().toString()); // Atualizando a data de cadastro ao atualizar o aluno
        return alunoRepository.save(aluno);
    }

    public void deletarAluno(Long id) {
        alunoRepository.deleteById(id);
    }
}
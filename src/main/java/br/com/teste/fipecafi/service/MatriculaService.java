package br.com.teste.fipecafi.service;

import br.com.teste.fipecafi.entity.Aluno;
import br.com.teste.fipecafi.entity.Curso;
import br.com.teste.fipecafi.entity.Turma;
import br.com.teste.fipecafi.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    public Aluno matricularAluno(Aluno aluno) {
       // Validações
        if (aluno.getNome() == null || aluno.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do aluno é obrigatório.");
        }
        if (aluno.getCurso() == null || aluno.getTurma() == null) {
            throw new IllegalArgumentException("Curso e Turma não podem ser nulos.");
        }

        aluno.setCodigoMatricula(gerarCodigoMatricula());

        // Formatar a data atual como String
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        aluno.setDataCadastro(LocalDateTime.now().format(formatter)); // Converte para String

        return matriculaRepository.save(aluno);
    }

    private Integer gerarCodigoMatricula() {
        return (int) (Math.random() * 100000); // Gera um número aleatório
    }
}
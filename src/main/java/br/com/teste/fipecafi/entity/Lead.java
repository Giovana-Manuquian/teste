        package br.com.teste.fipecafi.entity;

    import jakarta.persistence.*;

    @Entity
    @Table(name = "leads")  // Garantindo que a tabela se chama "leads" no banco de dados
    public class Lead {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nome;
        private String telefone;
        private String email;

        @ManyToOne
        @JoinColumn(name = "curso_id", nullable = false)
        private Curso curso;

        // Getters e Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Curso getCurso() {
            return curso;
        }

        public void setCurso(Curso curso) {
            this.curso = curso;
        }
    }

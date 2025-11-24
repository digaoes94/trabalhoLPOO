package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "funcionarios")
public class Funcionario extends Pessoa {

    @Column(name = "matricula_funcionario", nullable = false, unique = true)
    private String matriculaFuncionario;

    @Column(nullable = false)
    private String senha;
    
    public Funcionario() {
        super();
    }

    // --- CONSTRUTOR SEM SENHA (Legado/Opcional) ---
    public Funcionario(String nome, String cpf, String email, String celular, String matriculaFuncionario) {
        super(nome, cpf, email, celular);
        this.matriculaFuncionario = matriculaFuncionario;
    }

    // --- CONSTRUTOR COMPLETO (Usado na Tela de Cadastro) ---
    public Funcionario(String nome, String cpf, String email, String celular, String matriculaFuncionario, String senha) {
        super(nome, cpf, email, celular);
        this.matriculaFuncionario = matriculaFuncionario;
        this.senha = senha;
    }

    public String getMatriculaFuncionario() {
        return matriculaFuncionario;
    }

    public void setMatriculaFuncionario(String matriculaFuncionario) {
        this.matriculaFuncionario = matriculaFuncionario;
    }
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Funcionario [id=" + getId() + ", nome=" + getNome() + ", matricula=" + matriculaFuncionario + "]";
    }
}

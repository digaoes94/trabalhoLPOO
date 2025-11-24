package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "funcionarios")
public class Funcionario extends Pessoa {

	@Column(name = "matricula_funcionario", nullable = false, unique = true)
	private String matriculaFuncionario;

	public Funcionario() {

	}

	public Funcionario(String nome, String cpf, String email, String celular, String matriculaFuncionario) {
		super(nome, cpf, email, celular);
		this.matriculaFuncionario = matriculaFuncionario;
	}

	public String getMatriculaFuncionario() {
		return matriculaFuncionario;
	}

	public void setMatriculaFuncionario(String matriculaFuncionario) {
		this.matriculaFuncionario = matriculaFuncionario;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + getId() + ", nome=" + getNome() + ", matricula=" + matriculaFuncionario + "]";
	}
}
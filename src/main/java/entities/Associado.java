package entities;

import jakarta.persistence.*;

@Entity // MARCA COMO UMA ENTIDADE JPA E SERÁ A TABELA PRINCIPAL
@Table(name = "associados")
public class Associado extends Visitante {

	@Column(name = "matricula_do_associado", nullable = false, unique = true)
	private String matriculaAssociado;

	@Column(name = "senha", nullable = false)
	private String senha;


	public Associado(Visitante vis, String matriculaAssociado, String senha) {
		super(vis.getNome(), vis.getCpf(), vis.getEmail(), vis.getCelular());
		this.divida = vis.getDivida();
		this.matriculaAssociado = matriculaAssociado;
		this.senha = senha;
	}

	public Associado(String nome, String cpf, String email, String celular, String matriculaAssociado, String senha) {
		super(nome, cpf, email, celular);
		this.matriculaAssociado = matriculaAssociado;
		this.senha = senha;
	}
	
	public String getMatriculaAssociado() {
		return matriculaAssociado;
	}

	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

}

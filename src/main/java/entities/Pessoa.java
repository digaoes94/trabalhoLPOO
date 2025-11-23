package entities;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Pessoa {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) protected int id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "cpf_pessoa", unique = true, nullable = false)
    private String cpf;
	private String email, celular;

	public Pessoa(String nome, String cpf, String email, String celular) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.celular = celular;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", cpf=" + cpf + ", email=" + email + ", celular=" + celular + "]";
	}
}

package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "documentos")
@Inheritance(strategy = InheritanceType.JOINED) // CRIA A TABELA "documento" E AS TABELAS FILHAS LIGADAS POR ID
public abstract class Documento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	@Column(nullable = false)
	private String nome;

	private String genero;

	@Column(length = 500)
	private String descricao;

	public Documento() {
	}

	public Documento(String nome, String genero, String descricao) {
		this.nome = nome;
		this.genero = genero;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Documento [id=" + id + ", nome=" + nome + ", genero=" + genero + ", descricao=" + descricao;
	}

}

package entities;

import entities.interfaces.DocFisico;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "livros")
public class Livro extends Documento implements DocFisico {

	@Column(nullable = false)
	private String autor;

	// ------ATRIBUTO DA INTERFACE---------
	@Column(name = "localizacao_estante")
	private String localizacao;

	public Livro() {
	}

	public Livro(String nome, String genero, String descricao, String localizacao, String autor) {
		super(nome, genero, descricao);
		this.localizacao = localizacao;
		this.autor = autor;
	}

	
	//------METODOS DA INTERFACE---------
	@Override
	public String getLocalizacao() {
		return localizacao;
	}

	@Override
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	//----------------------------------
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String localizar() {
		return String.format("O livro %s se encontra em %s", this.getNome(), this.getLocalizacao());
	}

	@Override
	public String toString() {
		return "Livro [id=" + getId() + ", nome=" + getNome() + ", autor=" + autor + "]";
	}
}
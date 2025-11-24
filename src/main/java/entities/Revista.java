package entities;

import entities.interfaces.DocFisico;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "revistas")
public class Revista extends Documento implements DocFisico {

	@Column(nullable = false)
	private String fotografo;

	@Column(nullable = false)
	private int edicao;

	// ------ATRIBUTO DA INTERFACE---------
	@Column(name = "localizacao_estante")
	private String localizacao;

	public Revista() {
	}

	public Revista(String nome, String genero, String descricao, String localizacao, String fotografo, int edicao) {
		super(nome, genero, descricao);
		this.localizacao = localizacao;
		this.fotografo = fotografo;
		this.edicao = edicao;
	}

	// --------METODOS DA INTERFACE--------
	@Override
	public String getLocalizacao() {
		return localizacao;
	}

	@Override
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	// -----------------------------------

	public String getFotografo() {
		return fotografo;
	}

	public int getEdicao() {
		return edicao;
	}

	@Override
	public String localizar() {
		return String.format("A revista %s se encontra em %s", this.getNome(), this.getLocalizacao());
	}

	@Override
	public String toString() {
		return super.toString() + ". Revista [fotografo=" + fotografo + ", edicao=" + edicao + "]";
	}
}
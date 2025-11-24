package entities;

import entities.interfaces.DocFisico;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "periodicos")
public class Periodico extends Documento implements DocFisico {

	@Column(nullable = false)
	private String revisor;

	private String area;
	private String subarea;

	// ------ATRIBUTO DA INTERFACE---------
	@Column(name = "localizacao_estante")
	private String localizacao;

	public Periodico() {
	}

	public Periodico(String nome, String genero, String descricao, String localizacao, String revisor, String area,
			String subarea) {
		super(nome, genero, descricao);

		this.localizacao = localizacao;
		this.revisor = revisor;
		this.area = area;
		this.subarea = subarea;
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
	// -------------------------------------

	public String getRevisor() {
		return revisor;
	}

	public String getArea() {
		return area;
	}

	public String getSubarea() {
		return subarea;
	}

	@Override
	public String localizar() {
		return String.format("O periodico %s se encontra em %s", this.getNome(), this.getLocalizacao());
	}

	@Override
	public String toString() {
		return super.toString() + ". Periodico [revisor=" + revisor + ", area=" + area + ", subarea=" + subarea + "]";
	}

}

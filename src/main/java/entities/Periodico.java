package entities;

import entities.interfaces.DocFisico;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "periodicos")
public class Periodico extends Documento implements DocFisico {
	@Column(nullable = false) private String revisor;
	private String area, subarea;

	public Periodico(String nome, String genero, String descricao, String localizacao, String revisor, String area, String subarea) {
		super(nome, genero, descricao, localizacao);
		this.revisor = revisor;
		this.area = area;
		this.subarea = subarea;
	}

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
	public String toString() {
		return super.toString() + ". Periodico [revisor=" + revisor + ", area=" + area + ", subarea=" + subarea + "]";
	}
	
	public Periodico pesquisarPeriodico(int id) {
		return bancoDados.findById(id);
	}
	public boolean adicionarPeriodico(Periodico periodico) {
		return bancoDados.add(periodico);
	}
	public boolean excluirPeriodico(int id) {
		return bancoDados.delete(pesquisarPeriodico(id));
	}

	@Override
	public String localizar() {
		return String.format("O periodico %s se encontra em %s", this.getNome(), this.getLocalizacao());
	}
	
	
}

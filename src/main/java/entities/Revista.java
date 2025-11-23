package entities; 

import entities.interfaces.DocFisico;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "revistas")
public class Revista extends Documento implements DocFisico {
	@Column(nullable = false) private String fotografo;
	@Column(nullable = false) private int edicao;
	
	public Revista(String nome, String genero, String descricao, String localizacao, String fotografo, int edicao) {
		super(nome, genero, descricao, localizacao);
		this.fotografo = fotografo;
		this.edicao = edicao;
	}

	public String getFotografo() {
		return fotografo;
	}
	public int getEdicao() {
		return edicao;
	}

	@Override
	public String toString() {
		return super.toString() + ". Revista [fotografo=" + fotografo + ", edicao=" + edicao + "]";
	}
	
	public Revista pesquisarRevista(int id) {
		return bancoDados.findById(id);
	}
	public boolean adicionarRevista(Revista revista) {
		return bancoDados.add(revista);
	}
	public boolean excluirRevista(int id) {
		return bancoDados.delete(pesquisarRevista(id));
	}

	@Override
	public String localizar() {
		return String.format("A revista %s se encontra em %s", this.getNome(), this.getLocalizacao());
	}
}
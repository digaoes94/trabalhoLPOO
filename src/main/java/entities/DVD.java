package entities;

import entities.interfaces.DocDigital;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "dvds")
public class DVD extends Documento implements DocDigital {

	@Column(name = "is_executavel")
	private Boolean executavel;

	public DVD() {
	}

	public DVD(String nome, String genero, String descricao, Boolean executavel) {
		super(nome, genero, descricao);
		this.executavel = executavel;
	}

	@Override
	public Boolean getExecutavel() {
		return executavel;
	}

	@Override
	public void setExecutavel(Boolean executavel) {
		this.executavel = executavel;
	}

	@Override
	public void executar() {
		System.out.println("Executando o DVD: " + this.getNome());
	}

}
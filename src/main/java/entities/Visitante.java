package entities;

import jakarta.persistence.*;
import java.util.List;

@Entity //MARCA COMO UMA ENTIDADE JPA E SERÁ A TABELA PRINCIPAL
@Table(name = "visitantes")  
public class Visitante extends Pessoa{
	
	@Column(name = "divida", nullable = false)
	protected Double divida = 0.0;
	
	public Visitante() {}
	
	public Visitante(String nome, String cpf, String email, String celular) {
		super(nome, cpf, email, celular);
		this.divida = 0.0;
	}

	public double getDivida() {
		return divida;
	}
	public void setDivida(double divida) {
		this.divida = divida;
	}
	
	@Override
	public String toString() {
		return super.toString() + " Visitante [divida=" + divida + "]";
	}
	
}

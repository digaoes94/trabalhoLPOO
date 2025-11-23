package entities;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

import data.DB;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Documento {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) protected int id;
	
	@Column(nullable = false) private String nome;
	private String genero, descricao, localizacao;

	public Documento(String nome, String genero, String descricao, String localizacao) {
		this.nome = nome;
		this.genero = genero;
		this.descricao = descricao;
		this.localizacao = localizacao;
	}

	public int getId() {
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

	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	
	@Override
	public String toString() {
		return "Documento [id=" + id + ", nome=" + nome + ", genero=" + genero + ", descricao=" + descricao
				+ ", localizacao=" + localizacao + "]";
	}

	public ArrayList<String> verEmprestimos() {
		ArrayList<Object> lista;
		String row;
		
		Connection conn = DB.getConnection();
		Statement state = conn.createStatement();
		ResultSet res;
		
		res = state.executeQuery("SELECT * FROM emprestimos");
		
		while(res.next()) {
			
		}
		
		return bancoDados.findAll(id);
	}
}

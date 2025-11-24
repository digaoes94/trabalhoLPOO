package entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data.DB;
import data.exceptions.DBExceptions;
import jakarta.persistence.*;

@Entity // MARCA COMO UMA ENTIDADE JPA E SERÁ A TABELA PRINCIPAL
@Table(name = "funcionarios")
public class Funcionario extends Pessoa {

	@Column(name = "matricula_do_funcionário", nullable = false, unique = true)
	private String matriculaFuncionario;

	public Funcionario(String nome, String cpf, String email, String celular, String matriculaFuncionario) {
		super(nome, cpf, email, celular);
		this.matriculaFuncionario = matriculaFuncionario;
	}

	public String getMatriculaFuncionario() {
		return matriculaFuncionario;
	}
	public void setMatriculaFuncionario(String matriculaFuncionario) {
		this.matriculaFuncionario = matriculaFuncionario;
	}

	@Override
	public String toString() {
		return super.toString() + " Funcionario [matriculaFuncionario=" + matriculaFuncionario + "]";
	}

	// CRUD VISITANTE  ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// PESQUISAR VISITANTE  ---------------
	public Visitante pesquisarVisitante(int id) throws SQLException { //nas funções PESQUISAR seria sim interessante ter DAOs, mas isso é referência pro futuro
		try {
			Connection conn = DB.getConnection();
			Statement state = conn.createStatement();
			ResultSet res = state.executeQuery(String.format("SELECT * FROM funcionarios WHERE funcionario.id = %s", id));

			return refazerVisitante(res);
		}
		catch(SQLException e) {
			throw new DBExceptions(e.getMessage());
		}
		finally {
			DB.closeConnection();
		}
	}
	// ADICIONAR VISITANTE ----------------
	public boolean adicionarVisitante(Visitante vis) throws SQLException {
		if(pesquisarVisitante(vis.getId()) == null) {
			return false;
		}
		else {
			try {
				Connection conn = DB.getConnection();
				Statement state = conn.createStatement();
				ResultSet res = state.executeQuery(String.format("INSERT INTO visitantes (%s, %s, %s, %s, %f)", vis.getNome(), vis.getCpf(), vis.getEmail(), vis.getCelular(), vis.getDivida()));

				return true;
			}
			finally {
				DB.closeConnection();
			}
		}
	}
	// ALTERAR VISITANTE ---------------------
	public boolean alterarVisitante(int id) {
		return bancoDados.update(id);
	}
	// EXCLUIR VISITANTE ---------------------
	public boolean excluirVisitante(int id) throws SQLException {
		if(pesquisarVisitante(id) == null) {
			return false;
		}
		else {
			try {
				Connection conn = DB.getConnection();
				Statement state = conn.createStatement();
				ResultSet res = state.executeQuery(String.format("DELETE FROM visitantes WHERE id = %d", id));
			}
			finally {
				DB.closeConnection();
			}
			return true;
		}
	}
	// EXTRA, REFAZER VISITANTE (como a ideia é retornar um Visitante, mas o ResultSet precisa ser iterado coluna por coluna, esta função itera a linha do RS e monta o Visitante para retorno)
	private Visitante refazerVisitante(ResultSet res) throws SQLException {
		Visitante aux = new Visitante();

		if(!res.next()) {
			return null;
		}
		else {
			aux.setId(res.getInt("Id"));
			aux.setNome(res.getString("Nome"));
			aux.setCpf(res.getString("cpf"));
			aux.setEmail(res.getString("email"));
			aux.setCelular(res.getString("celular"));
			aux.setDivida(res.getDouble("divida"));
		}
		return aux;
	}
}

	//  CRUD ASSOCIADO  -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public Associado pesquisarAssociado(int id) {
		return bancoDados.findById(id);
	}
	public boolean adicionarAssociado(Visitante vis, String matricula, String senha) {
		Associado a = new Associado(vis, matricula, senha);
		return bancoDados.add(a);
	}
	public boolean adicionarAssociado(Associado ass) {
		return bancoDados.add(ass);
	}
	public boolean alterarAssociado(int id) {
		return bancoDados.update(id);
	}
	public boolean excluirAssociado(int id) {
		return bancoDados.delete(id);
	}
	
	private boolean registrarEmprestimo(EmprestimoLivro emp) {
		return bancoDados.add(emp);
	}
	private boolean registrarDevolucao(EmprestimoLivro emp) {
		//precisa ver se vai ter divida e adicionar se necessário
		return bancoDados.delete(emp);
	}

}

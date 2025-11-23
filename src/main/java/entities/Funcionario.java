package entities;

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
	
	public Visitante pesquisarVisitante(int id) {
		return bancoDados.findById(id);
	}
	public boolean adicionarVisitante(Visitante vis) {
		return bancoDados.add(vis);
	}
	public boolean alterarVisitante(int id) {
		return bancoDados.update(id);
	}
	public boolean excluirVisitante(int id) {
		return bancoDados.delete(id);
	}
	
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

package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity // MARCA COMO UMA ENTIDADE JPA E SERÁ A TABELA PRINCIPAL
@Table(name = "empréstimo")

public class EmprestimoLivro {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;

	@Column(name = "cpf_do_mutuario", nullable = false)
	private String cpfMutuario;
	
	private int id_livro
	
	@Column(name = "inicio_do_emprestimo", nullable = false)
	private LocalDate inicioEmprestimo;
	
	@Column(name = "fim_do_emprestimo", nullable = false)
	private LocalDate fimEmprestimo; // DATA PREVISTA PARA A ENTREGA
	
	@Column(name = "data_da_real_devolucao")
	private LocalDate dataRealDevolucao; // DATA DEFINITIVA DA ENTREGA
	
	@Column(name = "status", nullable = false)
	private boolean status;

	public EmprestimoLivro() {

	}

	public EmprestimoLivro(String cpfMutuario, LocalDate inicioEmprestimo, LocalDate fimEmprestimo,LocalDate dataRealDevolucao, boolean status) {
		this.cpfMutuario = cpfMutuario;
		this.inicioEmprestimo = inicioEmprestimo;
		this.fimEmprestimo = fimEmprestimo;
		this.dataRealDevolucao = dataRealDevolucao;
		this.status = true;
	}
	
	public String getCpfMutuario() {
		return cpfMutuario;
	}
	public void setCpfMutuario(String cpfMutuario) {
		this.cpfMutuario = cpfMutuario;
	}

	public LocalDate getInicioEmprestimo() {
		return inicioEmprestimo;
	}
	public void setInicioEmprestimo(LocalDate inicioEmprestimo) {
		this.inicioEmprestimo = inicioEmprestimo;
	}

	public LocalDate getFimEmprestimo() {
		return fimEmprestimo;
	}
	public void setFimEmprestimo(LocalDate fimEmprestimo) {
		this.fimEmprestimo = fimEmprestimo;
	}

	public LocalDate getDataRealDevolucao() {
		return dataRealDevolucao;
	}
	public void setDataRealDevolucao(LocalDate dataRealDevolucao) {
		this.dataRealDevolucao = dataRealDevolucao;
	}

	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Emprestimo [id=" + id + ", cpfMutuario=" + cpfMutuario + ", inicioEmprestimo=" + inicioEmprestimo
				+ ", fimEmprestimo=" + fimEmprestimo + ", dataRealDevolucao=" + dataRealDevolucao + ", status=" + status
				+ "]";
	}
	
	public Double calculaDivida() {
		if(LocalDateTime.now().isBefore(this.fimEmprestimo) || LocalDateTime.now().isEqual(this.fimEmprestimo)) {
			return 0.0;
		}
		else {
			return 1.0 * days;
		}
	}

}

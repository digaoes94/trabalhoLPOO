package entities;

import java.util.Date;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.*;

@Entity // MARCA COMO UMA ENTIDADE JPA E SERÁ A TABELA PRINCIPAL
@Table(name = "empréstimo")

public class EmprestimoLivro {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;

	@Column(name = "cpf_mutuario", nullable = false)
	private String cpfMutuario;
	
	@Column(name = "cpf_livro", nullable = false)
	private int idLivro;
	
	@Column(name = "inicio", nullable = false)
	private Date inicio;
	
	@Column(name = "fim", nullable = false)
	private Date fim;
	
	@Column(name = "data_devolucao")
	private Date dataDevolucao;
	
	@Column(name = "status", nullable = false)
	private boolean status;

	public EmprestimoLivro(String cpfMutuario, Livro livro, boolean status) {
		Instant start = Instant.now();
		Instant later = start.plus(14, ChronoUnit.DAYS);
		
		this.cpfMutuario = cpfMutuario;
		this.idLivro = livro.getId();
		this.inicio = Date.from(start);
		this.fim = Date.from(later);
		this.dataDevolucao = null;
		this.status = true;
	}
	
	public String getCpfMutuario() {
		return cpfMutuario;
	}

	public int getIdLivro() {
		return idLivro;
	}

	public Date getInicioEmprestimo() {
		return inicio;
	}

	public Date getFimEmprestimo() {
		return fim;
	}
	public void setFimEmprestimo(Date fim) {
		this.fim = fim;
	}

	public void setDataDevolucao(Visitante v) {
		this.dataDevolucao = Date.from(Instant.now());
		
		if(v instanceof Associado) {
			Associado a = (Associado) v;
			a.setDivida(a.getDivida() + calculaDivida());
		}
		else {
			v.setDivida(v.getDivida() + calculaDivida());
		}
	}

	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Emprestimo [id=" + id + ", cpfMutuario=" + cpfMutuario + ", inicioEmprestimo=" + inicio
				+ ", fimEmprestimo=" + fim + ", dataRealDevolucao=" + dataDevolucao + ", status=" + status
				+ "]";
	}
	
	public Double calculaDivida() {
		int atraso = dataDevolucao.compareTo(fim);
		
		if(atraso == 0 || atraso < 0) {
			return 0.0;
		}
		else {
			return (double) Duration.between(dataDevolucao.toInstant(), inicio.toInstant()).toDays() * 1.0;
		}
	}
}

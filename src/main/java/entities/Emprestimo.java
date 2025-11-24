package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "emprestimos")
public class Emprestimo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // ID PRÓPRIO DE EMPRESTIMO

	// POLIMORFISMO COM O HIBERNATE
	// O HIBERNATE VAI OLHAR O ID E SABER SE É LIVRO, DVD, PDF, etc.
	@ManyToOne
	@JoinColumn(name = "documento_id", nullable = false)
	private Documento documento;

	@Column(name = "cpf_mutuario", nullable = false)
	private String cpfMutuario;

	@Column(name = "inicio_emprestimo", nullable = false)
	private LocalDate inicioEmprestimo;

	@Column(name = "fim_emprestimo", nullable = false)
	private LocalDate fimEmprestimo; // DATA PREVISTA PARA A DEVOLUÇÃO.

	@Column(name = "data_real_devolucao")
	private LocalDate dataRealDevolucao; // DATA REAL PARA A DEVOLUÇÃO. SE MANTÉM NULL SE AINDA NÃO DEVOLVEU

	@Column(nullable = false)
	private Boolean status; // TRUE = ABERTO / FALSE = FINALIZADO

	public Emprestimo() {
	}

	public Emprestimo(Documento documento, String cpfMutuario, LocalDate inicioEmprestimo, LocalDate fimEmprestimo) {
		this.documento = documento;
		this.cpfMutuario = cpfMutuario;
		this.inicioEmprestimo = inicioEmprestimo;
		this.fimEmprestimo = fimEmprestimo;
		this.status = true; // AO CRIAR, ESTÁ ABERTO
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public boolean estaAtrasado() {
		// SE AINDA ESTIVER ABERTO (status = true)
		if (this.status && LocalDate.now().isAfter(this.fimEmprestimo)) {
			return true;
		}
		return false;
	}
}
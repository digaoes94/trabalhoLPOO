package dao;

import java.util.List;

import data.JPAUtil;
import entities.PDF;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class PDFDAO {

	private EntityManager manager;

	public PDFDAO() {
		this.manager = JPAUtil.getEntityManager();
	}

	// SALVAR (SERVE PARA CADASTRAR NOVO OU ATULIZAR UM EXISTENTE);
	public void salvar(PDF pdf) {
		try {
			manager.getTransaction().begin(); // ABRE A TRANSAÇÃO

			if (pdf.getId() == null) { // PEGA O ID, SE NÃO TEM ID, ENTÃO É NOVO, -> SALVA.
				manager.persist(pdf);
			} else {
				manager.merge(pdf); // SE TEM ID, ENTÃO JÁ EXISTE, ATUALIZA.
			}

			manager.getTransaction().commit(); // CONFIRMA NO BANCO
		} catch (Exception e) {
			manager.getTransaction().rollback();// SE NÃO DER CERTO, REFAZ TUDO.
			throw e;
		}
	}

	public PDF buscarPorId(Integer id) {
		return manager.find(PDF.class, id);
	}

	public List<PDF> listarTodos() {
		String jpql = "SELECT l FROM Livro l";
		TypedQuery<PDF> query = manager.createQuery(jpql, PDF.class);
		return query.getResultList();
	}

	public void excluir(Integer id) {
		try {
			manager.getTransaction().begin();

			PDF pdf = manager.find(PDF.class, id);
			if (pdf != null) {
				manager.remove(pdf);
			}

			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}

	// FECHA A CONEXÃO COM ESTE DAO ESPECÍFICO.
	public void fechar() {
		manager.close();
	}
}

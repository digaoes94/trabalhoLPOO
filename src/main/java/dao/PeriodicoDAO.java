package dao;

import java.util.List;

import data.JPAUtil;
import entities.Periodico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class PeriodicoDAO {

	private EntityManager manager;

	public PeriodicoDAO() {
		this.manager = JPAUtil.getEntityManager();
	}

	// SALVAR (SERVE PARA CADASTRAR NOVO OU ATULIZAR UM EXISTENTE);
	public void salvar(Periodico periodico) {
		try {
			manager.getTransaction().begin(); // ABRE A TRANSAÇÃO

			if (periodico.getId() == null) { // PEGA O ID, SE NÃO TEM ID, ENTÃO É NOVO, -> SALVA.
				manager.persist(periodico);
			} else {
				manager.merge(periodico); // SE TEM ID, ENTÃO JÁ EXISTE, ATUALIZA.
			}

			manager.getTransaction().commit(); // CONFIRMA NO BANCO
		} catch (Exception e) {
			manager.getTransaction().rollback();// SE NÃO DER CERTO, REFAZ TUDO.
			throw e;
		}
	}

	public Periodico buscarPorId(Integer id) {
		return manager.find(Periodico.class, id);
	}

	public List<Periodico> listarTodos() {
		String jpql = "SELECT l FROM Livro l";
		TypedQuery<Periodico> query = manager.createQuery(jpql, Periodico.class);
		return query.getResultList();
	}

	public void excluir(Integer id) {
		try {
			manager.getTransaction().begin();

			Periodico periodico = manager.find(Periodico.class, id);
			if (periodico != null) {
				manager.remove(periodico);
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

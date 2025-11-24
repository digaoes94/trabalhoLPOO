package dao;

import java.util.List;

import data.JPAUtil;
import entities.Revista;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class RevistaDAO {

	private EntityManager manager;

	public RevistaDAO() {
		this.manager = JPAUtil.getEntityManager();
	}

	// SALVAR (SERVE PARA CADASTRAR NOVO OU ATULIZAR UM EXISTENTE);
	public void salvar(Revista revista) {
		try {
			manager.getTransaction().begin(); // ABRE A TRANSAÇÃO

			if (revista.getId() == null) { // PEGA O ID, SE NÃO TEM ID, ENTÃO É NOVO, -> SALVA.
				manager.persist(revista);
			} else {
				manager.merge(revista); // SE TEM ID, ENTÃO JÁ EXISTE, ATUALIZA.
			}

			manager.getTransaction().commit(); // CONFIRMA NO BANCO
		} catch (Exception e) {
			manager.getTransaction().rollback();// SE NÃO DER CERTO, REFAZ TUDO.
			throw e;
		}
	}

	public Revista buscarPorId(Integer id) {
		return manager.find(Revista.class, id);
	}

	public List<Revista> listarTodos() {
		String jpql = "SELECT l FROM Livro l";
		TypedQuery<Revista> query = manager.createQuery(jpql, Revista.class);
		return query.getResultList();
	}

	public void excluir(Integer id) {
		try {
			manager.getTransaction().begin();

			Revista revista = manager.find(Revista.class, id);
			if (revista != null) {
				manager.remove(revista);
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

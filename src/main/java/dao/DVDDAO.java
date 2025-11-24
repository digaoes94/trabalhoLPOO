package dao;

import java.util.List;

import data.JPAUtil;
import entities.DVD;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class DVDDAO {

	private EntityManager manager;

	public DVDDAO() {
		this.manager = JPAUtil.getEntityManager();
	}

	// SALVAR (SERVE PARA CADASTRAR NOVO OU ATULIZAR UM EXISTENTE);
	public void salvar(DVD dvd) {
		try {
			manager.getTransaction().begin(); // ABRE A TRANSAÇÃO

			if (dvd.getId() == null) { // PEGA O ID, SE NÃO TEM ID, ENTÃO É NOVO, -> SALVA.
				manager.persist(dvd);
			} else {
				manager.merge(dvd); // SE TEM ID, ENTÃO JÁ EXISTE, ATUALIZA.
			}

			manager.getTransaction().commit(); // CONFIRMA NO BANCO
		} catch (Exception e) {
			manager.getTransaction().rollback();// SE NÃO DER CERTO, REFAZ TUDO.
			throw e;
		}
	}

	public DVD buscarPorId(Integer id) {
		return manager.find(DVD.class, id);
	}

	public List<DVD> listarTodos() {
		String jpql = "SELECT l FROM Livro l";
		TypedQuery<DVD> query = manager.createQuery(jpql, DVD.class);
		return query.getResultList();
	}

	public void excluir(Integer id) {
		try {
			manager.getTransaction().begin();

			DVD dvd = manager.find(DVD.class, id);
			if (dvd != null) {
				manager.remove(dvd);
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

package dao;

import java.util.List;

import data.JPAUtil;
import entities.Visitante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class VisitanteDAO {

	private EntityManager manager;

	public VisitanteDAO() {
		this.manager = JPAUtil.getEntityManager();
	}

	// SALVAR (CADASTRA OU ATUALIZA).
	public void salvar(Visitante visitante) {
		try {
			manager.getTransaction().begin();

			// VERIFICA SE É NOVO.
			if (visitante.getId() == null) {
				manager.persist(visitante);
			} else {
				manager.merge(visitante);
			}

			manager.getTransaction().commit();
		} catch (Exception e) {
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
			throw e;
		}
	}

	public Visitante buscarPorId(int id) {
		return manager.find(Visitante.class, id);
	}

	public Visitante buscarPorCpf(String cpf) {
		try {
			String jpql = "SELECT v FROM Visitante v WHERE v.cpf = :cpf";
			return manager.createQuery(jpql, Visitante.class).setParameter("cpf", cpf).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public List<Visitante> listarTodos() {
		String jpql = "SELECT v FROM Visitante v";
		TypedQuery<Visitante> query = manager.createQuery(jpql, Visitante.class);
		return query.getResultList();
	}

	public void excluir(int id) {
		try {
			manager.getTransaction().begin();
			Visitante visitante = manager.find(Visitante.class, id);
			if (visitante != null) {
				manager.remove(visitante);
			}
			manager.getTransaction().commit();
		} catch (Exception e) {
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
			throw e;
		}
	}

	public void fechar() {
		manager.close();
	}
}

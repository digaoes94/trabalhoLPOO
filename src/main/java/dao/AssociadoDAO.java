package dao;

import java.util.List;

import data.JPAUtil;
import entities.Associado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class AssociadoDAO {

	private EntityManager manager;

	public AssociadoDAO() {
		this.manager = JPAUtil.getEntityManager();
	}

	// SALVAR (CADASTRA OU ATUALIZA).
	public void salvar(Associado associado) {
		try {
			manager.getTransaction().begin();
			if (associado.getId() == null || associado.getId() == 0) {
				manager.persist(associado);
			} else {
				manager.merge(associado);
			}
			manager.getTransaction().commit();
		} catch (Exception e) {
			if (manager.getTransaction().isActive())
				manager.getTransaction().rollback();
			throw e;
		}
	}

	public Associado buscarPorId(Integer id) {
		return manager.find(Associado.class, id);
	}

	//BUSCAR POR MATRÍCULA.
	public Associado buscarPorMatricula(String matricula) {
		try {
			String jpql = "SELECT a FROM Associado a WHERE a.matriculaAssociado = :mat";
			return manager.createQuery(jpql, Associado.class).setParameter("mat", matricula).getSingleResult();
		} catch (NoResultException e) {
			return null; //RETORNA NULL SE NÃO ACHAR.
		}
	}

	public List<Associado> listarTodos() {
		return manager.createQuery("SELECT a FROM Associado a", Associado.class).getResultList();
	}

	public void excluir(Integer id) {
		try {
			manager.getTransaction().begin();
			Associado associado = manager.find(Associado.class, id);
			if (associado != null) {
				manager.remove(associado);
			}
			manager.getTransaction().commit();
		} catch (Exception e) {
			if (manager.getTransaction().isActive())
				manager.getTransaction().rollback();
			throw e;
		}
	}

	public void fechar() {
		manager.close();
	}
}
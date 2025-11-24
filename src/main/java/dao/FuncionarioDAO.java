package dao;

import java.util.List;

import data.JPAUtil;
import entities.Funcionario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class FuncionarioDAO {

	private EntityManager manager;

	public FuncionarioDAO() {
		this.manager = JPAUtil.getEntityManager();
	}

	public void salvar(Funcionario funcionario) {
		try {
			manager.getTransaction().begin();
			if (funcionario.getId() == null || funcionario.getId() == 0) {
				manager.persist(funcionario);
			} else {
				manager.merge(funcionario);
			}
			manager.getTransaction().commit();
		} catch (Exception e) {
			if (manager.getTransaction().isActive())
				manager.getTransaction().rollback();
			throw e;
		}
	}

	public Funcionario buscarPorId(Integer id) {
		return manager.find(Funcionario.class, id);
	}

	//BUSCA POR MATRICULA.
	public Funcionario buscarPorMatricula(String matricula) {
		try {
			String jpql = "SELECT f FROM Funcionario f WHERE f.matriculaFuncionario = :mat";
			return manager.createQuery(jpql, Funcionario.class).setParameter("mat", matricula).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Funcionario> listarTodos() {
		return manager.createQuery("SELECT f FROM Funcionario f", Funcionario.class).getResultList();
	}

	public void excluir(Integer id) {
		try {
			manager.getTransaction().begin();
			Funcionario funcionario = manager.find(Funcionario.class, id);
			if (funcionario != null) {
				manager.remove(funcionario);
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

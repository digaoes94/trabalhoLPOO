package dto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
	private static final String PERSISTENCE_UNIT_NAME = "bibliotecadb";
	private static EntityManagerFactory factory;

	/**
	 * Retorna a EntityManagerFactory. Cria se ainda não existir. É este método que
	 * "acorda" o Hibernate e cria as tabelas.
	 */
	public static EntityManagerFactory getEntityManagerFactory() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		}
		return factory;
	}

	/**
	 * Retorna um EntityManager (a ferramenta para CRUD).
	 */
	public static EntityManager getEntityManager() {
		return getEntityManagerFactory().createEntityManager();
	}

	/**
	 * Fecha a fábrica de conexões (chamar no final da aplicação).
	 */
	public static void close() {
		if (factory != null && factory.isOpen()) {
			factory.close();
		}
	}
}

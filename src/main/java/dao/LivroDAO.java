package dao;

import java.util.List;

import data.JPAUtil;
import entities.Livro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class LivroDAO {

    private EntityManager manager;

    public LivroDAO() {
        this.manager = JPAUtil.getEntityManager();
    }

    //SALVAR (SERVE PARA CADASTRAR NOVO OU ATULIZAR UM EXISTENTE);
    public void salvar(Livro livro) {
        try {
            manager.getTransaction().begin(); //ABRE A TRANSAÇÃO
            
            if (livro.getId() == null) { //PEGA O ID, SE NÃO TEM ID, ENTÃO É NOVO, -> SALVA.
                manager.persist(livro); 
            } else {
                manager.merge(livro);   //SE TEM ID, ENTÃO JÁ EXISTE, ATUALIZA.
            }
            
            manager.getTransaction().commit(); //CONFIRMA NO BANCO
        } catch (Exception e) {
            manager.getTransaction().rollback();//SE NÃO DER CERTO, REFAZ TUDO.
            throw e;
        }
    }

    public Livro buscarPorId(Integer id) {
        return manager.find(Livro.class, id);
    }

    public List<Livro> listarTodos() {
        String jpql = "SELECT l FROM Livro l"; // JPQL (Java Persistence Query Language)
        TypedQuery<Livro> query = manager.createQuery(jpql, Livro.class);
        return query.getResultList();
    }

    public void excluir(Integer id) {
        try {
            manager.getTransaction().begin();
            
            Livro livro = manager.find(Livro.class, id);
            if (livro != null) {
                manager.remove(livro);
            }
            
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }
    }
    
    //FECHA A CONEXÃO COM ESTE DAO ESPECÍFICO.
    public void fechar() {
        manager.close();
    }
}

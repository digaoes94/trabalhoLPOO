package dao;

import data.JPAUtil;
import entities.Documento;
import java.util.List;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.EntityManager;

public class DocumentoDAO {

    private EntityManager manager;

    public DocumentoDAO() {
        this.manager = JPAUtil.getEntityManager();
    }

    //BUSCA QUALQUER COISA, (LIVRO, DVD, REVISTA) PELO ID
    public Documento buscarPorId(Integer id) {
        return manager.find(Documento.class, id);
    }

    public List<Documento> listarTodos() {
        String jpql = "SELECT d FROM Documento d";
        TypedQuery<Documento> query = manager.createQuery(jpql, Documento.class);
        return query.getResultList();
    }

    public void fechar() {
        manager.close();
    }

}

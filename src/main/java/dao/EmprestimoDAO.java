package dao;

import java.time.LocalDate;
import java.util.List;

import data.JPAUtil;
import entities.Emprestimo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class EmprestimoDAO {

    private EntityManager manager;

    public EmprestimoDAO() {
        this.manager = JPAUtil.getEntityManager();
    }

    //REGISTRA NOVO EMPRÉSTIMO
    public void salvar(Emprestimo emprestimo) {
        try {
            manager.getTransaction().begin();

            //SE É NOVO, SALVA, SE JÁ EXISTE, ATUALIZA.
            if (emprestimo.getId() == null || emprestimo.getId() == 0) {
                manager.persist(emprestimo);
            } else {
                manager.merge(emprestimo);
            }

            manager.getTransaction().commit();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            throw e;
        }
    }

    //BUSCAR POR ID.
    public Emprestimo buscarPorId(Integer id) {
        return manager.find(Emprestimo.class, id);
    }

    //LISTAR TODOS OS EMPRÉSTIMOS.
    public List<Emprestimo> listarTodos() {
        String jpql = "SELECT e FROM Emprestimo e";
        TypedQuery<Emprestimo> query = manager.createQuery(jpql, Emprestimo.class);
        return query.getResultList();
    }

    //LISTAR APENAS EMPRÉSTIMOS ABERTOS.
    public List<Emprestimo> listarEmprestimosAbertos() {
        String jpql = "SELECT e FROM Emprestimo e WHERE e.status = true";
        TypedQuery<Emprestimo> query = manager.createQuery(jpql, Emprestimo.class);
        return query.getResultList();
    }

    // 5. REALIZAR DEVOLUÇÃO
    public void finalizarEmprestimo(Integer id) {
        try {
            manager.getTransaction().begin();

            Emprestimo emp = manager.find(Emprestimo.class, id);
            if (emp != null && emp.getStatus()) { //SE EXISTE E ESTÁ ABERTO.
                emp.setStatus(false); //MARCA COMO FECHADO.
                emp.setDataRealDevolucao(LocalDate.now()); //DATA ATUAL.
                manager.merge(emp); //ATUALIZA O BANCO.
            }

            manager.getTransaction().commit();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            throw e;
        }
    }

    public boolean isDocumentoDisponivel(Integer idDocumento) {
        try {
            // Conta quantos empréstimos ABERTOS (status=true) existem para esse ID
            String jpql = "SELECT count(e) FROM Emprestimo e WHERE e.documento.id = :idDoc AND e.status = true";

            Long count = manager.createQuery(jpql, Long.class)
                    .setParameter("idDoc", idDocumento)
                    .getSingleResult();

            // Se a contagem for 0, significa que não tem empréstimo aberto. Está livre!
            return count == 0;

        } catch (Exception e) {
            // Se der erro na busca, assumimos que não está disponível por segurança
            return false;
        }
    }

    public void fechar() {
        manager.close();
    }
}

package com.jhssantiago.vendas.repository;
import com.jhssantiago.vendas.model.Venda;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jhons
 */
@Transactional
@Repository
public class VendaRepository {

    @PersistenceContext
    private EntityManager em;

    public Venda Venda(int idVenda) {
        return em.find(Venda.class, idVenda);
    }

    public void save(Venda venda) {
        em.persist(venda);
    }

    @SuppressWarnings("unchecked")
    public List<Venda> vendas() {
        Query query = em.createQuery("from Venda");
        return query.getResultList();
    }
    
    public void remove(int idVenda){
        Venda v = em.find(Venda.class, idVenda);
        em.remove(v);
    }
        
    public void update(Venda venda) {
        em.merge(venda);
    }
    
    @SuppressWarnings("unchecked")
    public List<Venda> venda(int idVenda){
        String sql = "from Venda as v where v.idVenda = :idVenda";
        Query query = em.createQuery(sql, Venda.class);
        query.setParameter("idVenda", idVenda);
        return query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public List<Venda> vendas(LocalDate localDate) {
        String hql = "from Venda as v where v.localDate = :localDate";
        Query query = em.createQuery(hql, Venda.class);
        query.setParameter("localDate", localDate);
        return query.getResultList();
    }
    
}

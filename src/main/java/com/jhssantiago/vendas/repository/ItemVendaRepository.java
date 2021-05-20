package com.jhssantiago.vendas.repository;

import com.jhssantiago.vendas.model.ItemVenda;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jhons
 */
@Transactional
@Repository
public class ItemVendaRepository {

    @PersistenceContext
    private EntityManager em;

    public ItemVenda ItemVenda(int idItemVenda) {
        return em.find(ItemVenda.class, idItemVenda);
    }

    public void save(ItemVenda itemVenda) {
        em.persist(itemVenda);
    }

    @SuppressWarnings("unchecked")
    public List<ItemVenda> itemVendas() {
        Query query = em.createQuery("from ItemVenda");
        return query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public List<ItemVenda> itemVendas(int idVenda) {
        String sql = "from ItemVenda as i where i.venda.idVenda = :idVenda";
        Query query = em.createQuery(sql,ItemVenda.class);
        query.setParameter("idVenda", idVenda);
        return query.getResultList();
    }

    public void remove(int idItemVenda) {
        ItemVenda i = em.find(ItemVenda.class, idItemVenda);
        em.remove(i);
    }
    
   public void update(ItemVenda itemVenda){
        em.merge(itemVenda);
    }
}

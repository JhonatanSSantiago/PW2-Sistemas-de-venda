package com.jhssantiago.vendas.dao;

import com.jhssantiago.vendas.model.ItemVenda;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
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

    public void remove(int idItemVenda) {
        ItemVenda i = em.find(ItemVenda.class, idItemVenda);
        em.remove(i);
    }
    
   public void update(ItemVenda itemVenda){
        em.merge(itemVenda);
    }
}

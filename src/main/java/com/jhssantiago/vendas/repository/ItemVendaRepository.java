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

    public void save(ItemVenda itemVenda) {//salva item
        em.persist(itemVenda);
    }

    public void remove(int idItemVenda) {//remove item
        ItemVenda i = em.find(ItemVenda.class, idItemVenda);
        em.remove(i);
    }

    public void update(ItemVenda itemVenda) {//atualiza item
        em.merge(itemVenda);
    }

    @SuppressWarnings("unchecked")
    public List<ItemVenda> itemVendas() { //lista todos itens de uma venda
        Query query = em.createQuery("from ItemVenda");
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<ItemVenda> itemVendas(int idVenda) { //lista todos itens de uma venda
        Query query = em.createQuery("from ItemVenda as i where i.venda.idVenda = :idVenda", ItemVenda.class);
        query.setParameter("idVenda", idVenda);
        return query.getResultList();
    }

}
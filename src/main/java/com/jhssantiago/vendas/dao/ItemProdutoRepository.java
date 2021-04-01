/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhssantiago.vendas.dao;

import com.jhssantiago.vendas.model.ItemVenda;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jhons
 */
@Repository
public class ItemProdutoRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    public ItemVenda ItemVenda(int id_itemVenda) {
        return em.find(ItemVenda.class, id_itemVenda);
    }
    
    @SuppressWarnings("unchecked")
    public List<ItemVenda> itemVendas(){
        Query query = em.createQuery("from ItemVenda");
        return query.getResultList();
    }
}

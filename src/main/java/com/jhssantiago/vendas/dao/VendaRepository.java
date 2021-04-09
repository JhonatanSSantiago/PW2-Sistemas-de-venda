/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhssantiago.vendas.dao;

import com.jhssantiago.vendas.model.Venda;
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
public class VendaRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    
    public void save(Venda venda) {
        em.persist(venda);
    }
    
    public Venda Venda(int idVenda) {
        return em.find(Venda.class, idVenda);
    }
    
    public List<Venda> vendas(){
        Query query = em.createQuery("from Venda");
        return query.getResultList();
    }
    
}

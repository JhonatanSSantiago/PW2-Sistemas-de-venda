/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhssantiago.vendas.dao;

import com.jhssantiago.vendas.model.Produto;
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
public class ProdutoRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    public Produto Produto(int idProduto) {
        return em.find(Produto.class, idProduto);
    }
    
    public void save(Produto produto) {
        em.persist(produto);
    }

    @SuppressWarnings("unchecked")
    public List<Produto> Produtos(){
        Query query = em.createQuery("from Produto");
        return query.getResultList();
    }
    
    public void remove(int idProduto) {
        Produto p = em.find(Produto.class, idProduto);
        em.remove(p);
    }

    public void update(Produto produto) {
        em.merge(produto);
    }
}

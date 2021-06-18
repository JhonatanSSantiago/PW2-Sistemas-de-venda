package com.jhssantiago.vendas.repository;

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
    
    public Produto produto(int idProduto) {
        return em.find(Produto.class, idProduto);
    }

    public void save(Produto produto) {
        em.persist(produto);
    }

    @SuppressWarnings("unchecked")
    public List<Produto> produtos() {
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
    
    @SuppressWarnings("unchecked")
    public List<Produto> buscarProduto(String nome) {
        Query query = em.createQuery("from Produto as p where p.nome like :nome", Produto.class);
        query.setParameter("nome", "%"+nome+"%");
        return query.getResultList();
    }

}

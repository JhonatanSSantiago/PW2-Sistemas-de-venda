package com.jhssantiago.vendas.dao;

import com.jhssantiago.vendas.model.ClientePF;
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
public class ClientePFRepository {
    
    @PersistenceContext
    private EntityManager em;

    public void save(ClientePF clientePF) {
        em.persist(clientePF);
    }

    public ClientePF clientePF(int id) {
        return em.find(ClientePF.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<ClientePF> clientesPF() {
        Query query = em.createQuery("from ClientePF");
        return query.getResultList();
    }

    public void remove(int id) {
        ClientePF pf = em.find(ClientePF.class, id);
        em.remove(pf);
    }

    public void update(ClientePF clientePF) {
        em.merge(clientePF);
    }
}

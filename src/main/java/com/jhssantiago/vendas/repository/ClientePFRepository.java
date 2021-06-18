package com.jhssantiago.vendas.repository;

import com.jhssantiago.vendas.model.ClientePF;
import com.jhssantiago.vendas.model.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jhons
 */
@Transactional
@Repository
public class ClientePFRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(ClientePF clientePF) {
        em.persist(clientePF);
    }

    public ClientePF clientePF(int idCliente) {
        return em.find(ClientePF.class, idCliente);
    }

    @SuppressWarnings("unchecked")
    public List<ClientePF> clientesPF() {
        Query query = em.createQuery("from ClientePF");
        return query.getResultList();
    }

    public void remove(int idCliente) {
        ClientePF pf = em.find(ClientePF.class, idCliente);
        em.remove(pf);
    }

    public void update(ClientePF clientePF) {
        em.merge(clientePF);
    }
    
    @SuppressWarnings("unchecked")
    public List<ClientePF> clientes(String nome) {
        Query query = em.createQuery("from ClientePF as c where c.nome like :nome", ClientePF.class);
        query.setParameter("nome", "%"+nome+"%");
        return query.getResultList();  
    }
    
    public ClientePF clientePF(Usuario usuario) {//Retorna um cliente pelo usuário
        TypedQuery<ClientePF> query = em.createQuery("from ClientePF as c where c.usuario = :usuario", ClientePF.class);
        query.setParameter("usuario",usuario);
        return query.getSingleResult();
    }
}

package com.jhssantiago.vendas.repository;
import com.jhssantiago.vendas.model.Venda;
import com.jhssantiago.vendas.model.ClientePF;
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
    public List<Venda> venda(int idVenda){ //Listar todas as vendas
        Query query = em.createQuery("from Venda as v where v.idVenda = :idVenda", Venda.class);
        query.setParameter("idVenda", idVenda);
        return query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public List<Venda> vendasData(LocalDate localDate) { //Listar por data
        Query query = em.createQuery("from Venda as v where v.localDate = :localDate", Venda.class);
        query.setParameter("localDate", localDate);
        return query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public List<Venda> clienteVendas(ClientePF clientePF) { //Listar Vendas de um cliente
        Query query = em.createQuery("from Venda as v where v.cliente = :clientePF", Venda.class);
        query.setParameter("clientePF", clientePF);
        return query.getResultList();
    }    
}

package com.jhssantiago.vendas.repository;

import com.jhssantiago.vendas.model.ClientePF;
import com.jhssantiago.vendas.model.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jhons
 */
@Repository
public class UsuarioRepository {

    @PersistenceContext
    private EntityManager em;

    public Usuario usuario(String login) {
        TypedQuery<Usuario> query = em.createQuery("from Usuario as u where u.login = :login", Usuario.class);
        query.setParameter("login", login);
        return query.getSingleResult();
    }

    public Usuario usuario(int idUsuario) {
        return em.find(Usuario.class, idUsuario);
    }

    public void save(Usuario usuario) {
        em.persist(usuario);
    }

    public void update(Usuario usuario) {
        em.merge(usuario);
    }
    
    public void remove(int idUsuario){
        Usuario u = em.find(Usuario.class, idUsuario);
        em.remove(u);
    }
  
    public Usuario usuarioCliente(ClientePF cliente) { //Retorna usuario pelo cliente
        TypedQuery<Usuario> query = em.createQuery("from Usuario as u where u.cliente = :cliente", Usuario.class);
        query.setParameter("cliente", cliente);
        return query.getSingleResult();
    }

}

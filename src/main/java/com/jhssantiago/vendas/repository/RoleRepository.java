package com.jhssantiago.vendas.repository;

import com.jhssantiago.vendas.model.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jhons
 */
@Transactional
@Repository
public class RoleRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    public Role role(int idRole) {
        return em.find(Role.class, idRole);
    }
}

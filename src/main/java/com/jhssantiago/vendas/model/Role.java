/*
public String criptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
 */
package com.jhssantiago.vendas.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
/**
 *
 * @author jhons
 */
@Table(name = "tb_role")
@Entity
public class Role implements GrantedAuthority{
    
    @Id
    private int idRole;
    
    private String nome;
    
    @ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios = new ArrayList<>();
    
    @Override
    public String getAuthority(){
        return nome;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
       
}

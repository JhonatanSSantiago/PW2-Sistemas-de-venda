package com.jhssantiago.vendas.security;

import com.jhssantiago.vendas.repository.UsuarioRepository;
import com.jhssantiago.vendas.model.Usuario;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jhons
 */
@Transactional
@Repository
public class UsuarioDetailsConfig implements UserDetailsService{
    
    @Autowired
    UsuarioRepository repository;
    
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = repository.usuario(login);
        if(usuario == null){
            throw new UsernameNotFoundException("Usuário não cadastrado");
        }
        return new User(usuario.getLogin(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());
    }
    
}

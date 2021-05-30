package com.jhssantiago.vendas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests() //define com as requisições HTTP devem ser tratadas com relação à segurança.
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/produtos/registration").hasAnyRole("ADMIN")
                .antMatchers("/produtos/list").hasAnyRole("ADMIN")
                .antMatchers("/clientes/form").hasAnyRole("ADMIN")
                .antMatchers("/clientes/list").hasAnyRole("ADMIN")
                .antMatchers("/vendas/saleslist").hasAnyRole("ADMIN")
                .anyRequest() //define que a configuração é válida para qualquer requisição.
                .authenticated() //define que o usuário precisa estar autenticado.
                .and()
                .formLogin() //define que a autenticação pode ser feita via formulário de login.
                .loginPage("/login") // passamos como parâmetro a URL para acesso à página de login que criamos
                .permitAll() //define que essa página pode ser acessada por todos, independentemente do usuário estar autenticado ou não.
                .and()
                .logout()
                .permitAll();
    }

    @Autowired
    public void configureUserDetails(AuthenticationManagerBuilder builder)
            throws Exception {
        builder
                .inMemoryAuthentication()
                .withUser("teste").password("$2a$10$wTcCFdoXbd6dY./pZxhdOuUytI1SjMPyHCGTe8rS23s9O/xoh9Yty").roles("EDITOR");
        builder
                .inMemoryAuthentication()
                .withUser("admin").password("$2a$10$WB2Kq8ELCz2qFynlpjINpuGcmC9Lb7OfyemLLluIII3aYKeHKctRq").roles("ADMIN");
    }

     /**
     * Com o método, instanciamos uma instância do encoder BCrypt e deixando o controle dessa instância como responsabilidade do Spring.
     * Agora, sempre que o Spring Security necessitar disso, ele já terá o que precisa configurado.
     * @return 
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } 

}

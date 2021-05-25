package com.jhssantiago.vendas.security;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author jhons
 */
public class GeradorSenha {
    public static void main(String[]args) {
        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }
}

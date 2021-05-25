/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhssantiago.vendas.controller;
import com.jhssantiago.vendas.model.ClientePF;
import javax.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author jhons
 */
@Transactional
@Controller
public class ControllerLogin {
    
    @GetMapping("/login")
    public String form(){
        return "autenticacao/login";
    }
}

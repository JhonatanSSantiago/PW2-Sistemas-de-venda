/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhssantiago.vendas.controller;
import com.jhssantiago.vendas.repository.ClientePFRepository;
import com.jhssantiago.vendas.model.ClientePF;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author jhons
 */
@Transactional
@Controller
@RequestMapping("clientes")
public class ControllerClientePF {
    
    @Autowired
    ClientePFRepository repository;

    /**
     * @param clientePF necessário devido utilizar no form.html o th:object que faz
     * referência ao objeto esperado no controller.
     * @return
     */
    @GetMapping("/form")
    public ModelAndView form(ClientePF clientePF) {
        return new ModelAndView("/clientes/form");
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("clientePF", repository.clientesPF());
        return new ModelAndView("/clientes/list", model);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid ClientePF clientePF, BindingResult result) {
        if(result.hasErrors()){
            return form(clientePF);
        }
        repository.save(clientePF);
        return new ModelAndView("redirect:/clientes/list");
    }

    
    /**
     * @param idCliente
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada
     * diretamente na URL
     */
    @GetMapping("/remove/{idCliente}")
    public ModelAndView remove(@PathVariable("idCliente") int idCliente) {
        repository.remove(idCliente);
        return new ModelAndView("redirect:/clientes/list");
    }

    /**
     * @param id
     * @param model
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada
     * diretamente na URL
     */
    @GetMapping("/edit/{idCliente}")
    public ModelAndView edit(@PathVariable("idCliente") int idCliente, ModelMap model) {
        model.addAttribute("clientePF", repository.clientePF(idCliente));
        return new ModelAndView("/clientes/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(ClientePF clientePF) {
        repository.update(clientePF);
        return new ModelAndView("redirect:/clientes/list");
    }
    
    @GetMapping("/buscarforname")
    public ModelAndView buscarforname(@RequestParam(value = "nome") String nome, ModelMap model) {
        model.addAttribute("clientePF", repository.clientes(nome));
        return new ModelAndView("/clientes/list");
    }
}

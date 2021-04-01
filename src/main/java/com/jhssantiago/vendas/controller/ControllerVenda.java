package com.jhssantiago.vendas.controller;

import com.jhssantiago.vendas.dao.VendaRepository;
import com.jhssantiago.vendas.model.Venda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jhons
 */

@Transactional
@Controller
@RequestMapping("vendas")
public class ControllerVenda {

    @Autowired
    VendaRepository repository;


    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("vendas", repository.vendas());
        return new ModelAndView("/vendas/list", model);

    }

}

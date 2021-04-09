package com.jhssantiago.vendas.controller;

import com.jhssantiago.vendas.dao.VendaRepository;
import com.jhssantiago.vendas.model.ItemVenda;
import com.jhssantiago.vendas.model.Venda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jhons
 */
@Scope("request")
@Transactional
@Controller
@RequestMapping("vendas")
public class ControllerVenda {

    @Autowired
    VendaRepository repository;
  
    @Autowired
    Venda venda;
       
    @ResponseBody
    @GetMapping("/teste")
    public String teste(){
        return "Controller Venda Funcionando";
    }
    
    @GetMapping("/form")
    public String form(Venda venda, ItemVenda itemvenda){
        return "/vendas/cadastroproduto";
    }
    
    
    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("vendas", repository.vendas());
        return new ModelAndView("/vendas/list", model);
    }
    
    @PostMapping("/add")
    public ModelAndView additem(ItemVenda itemvenda, ModelMap modelMap){
        itemvenda.setVenda(venda); //criar id de venda no form de carrinho
        venda.getItemVenda().add(itemvenda);
        return new ModelAndView("redirect:/vendas/carrinho");
    }       
    
    @PostMapping("/save")
    public ModelAndView save(Venda venda){
        this.venda.setId(0);
        this.venda.setData(venda.getData());
        repository.save(this.venda);
        return new ModelAndView("redirect:/vendas/list");
    }
}

package com.jhssantiago.vendas.controller;

import com.jhssantiago.vendas.dao.ProdutoRepository;
import com.jhssantiago.vendas.dao.VendaRepository;
import com.jhssantiago.vendas.model.ItemVenda;
import com.jhssantiago.vendas.model.Venda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.transaction.Transactional;

/**
 * @author jhons
 */
@Transactional
@Controller
@Scope("request")
@RequestMapping("vendas")
public class ControllerVenda {

    @Autowired
    VendaRepository vendarepository;

    @Autowired
    Venda venda;

    @Autowired
    ProdutoRepository produtorepository;

    @ResponseBody
    @GetMapping("/teste")
    public String teste() {
        return "Controller Venda Funcionando";
    }

   
    /**
     * @param venda necessário devido utilizar no form.html o th:object
     * que faz referência ao objeto esperado no controller.
     */
    @GetMapping("/form")
    public ModelAndView form(Venda venda) { //mostra lista de produtos
        ModelMap model = new ModelMap();
        model.addAttribute("produto", produtorepository.produtos()); //mostra lista de produtos
        model.addAttribute("itemVenda", new ItemVenda());
        model.addAttribute("venda", new Venda());
        venda.TotalVenda();
        return new ModelAndView("/vendas/form", model);
    }
   

    @GetMapping("/list")
    public ModelAndView listar(Venda venda, ModelMap model) { //lista de vendas
        model.addAttribute("vendas", vendarepository.vendas());
        return new ModelAndView("/vendas/list", model);
    }

    @PostMapping("/add")
    public ModelAndView add(ItemVenda itemVenda) {
        itemVenda.setVenda(venda);
        itemVenda.setProduto(produtorepository.produto(itemVenda.getProduto().getIdProduto()));
        itemVenda.TotalItem();
        venda.setItemVenda(itemVenda);
        venda.TotalVenda();
        return new ModelAndView("redirect:/vendas/form");
    }

    @PostMapping("/save")
    public ModelAndView save(Venda venda) {
        this.venda.setId(0);
        this.venda.setLocalDate(venda.getLocalDate());
        this.venda.TotalVenda();
        vendarepository.save(this.venda);
        this.venda.getItemVenda().clear();
        return new ModelAndView("redirect:/vendas/list");
    }
    
    /**
     * @PathVariable é utilizado quando o valor da variável é passada
     * diretamente na URL
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("venda", vendarepository.Venda(id));
        return new ModelAndView("/vendas/form", model);
    }
    
    @PostMapping("/update")
    public ModelAndView update(Venda venda) {
        vendarepository.update(venda);
        return new ModelAndView("redirect:/vendas/list");
    }

    @GetMapping("/remover/{id}")
    public ModelAndView remover(@PathVariable("id") int id){
        for(int i = 0; i < venda.getItemVenda().size(); i++){
            if(venda.getItemVenda().get(i).getProduto().getIdProduto()==(id)){
                venda.getItemVenda().remove(i);
            }
        }
        return new ModelAndView("redirect:/vendas/form");
    }
}

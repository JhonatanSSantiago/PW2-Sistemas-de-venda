package com.jhssantiago.vendas.controller;

import com.jhssantiago.vendas.dao.ProdutoRepository;
import com.jhssantiago.vendas.model.Produto;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jhons
 */
@Transactional
@Controller
@RequestMapping("produtos")
public class ControllerProdutos {
    
    @Autowired
    ProdutoRepository produtorepository;
     
    @ResponseBody
    @GetMapping("/teste")
    public String teste(){
        return "Controller Produto Funcionando";
    }
    /**
    * @param produto necessário devido utilizar no formproduto.html o th:object que faz
    * referência ao objeto esperado no controller.
    * @return
    */
    @GetMapping("/form")
    public ModelAndView form(Produto produto) {
       return new ModelAndView("/produtos/form");
    }
    
    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("produto", produtorepository.produtos());
        return new ModelAndView("/produtos/list", model);
    }
     
     @PostMapping("/save")
    public ModelAndView save(@Valid Produto produto, BindingResult result) {
        if(result.hasErrors()){
            return form(produto);
        }
        produtorepository.save(produto);
        return new ModelAndView("redirect:/produtos/list");
    }

    /**
     * @param idProduto
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada
     * diretamente na URL
     */
    @GetMapping("/remove/{idProduto}")
    public ModelAndView remove(@PathVariable("idProduto") int idProduto) {
        produtorepository.remove(idProduto);
        return new ModelAndView("redirect:/produtos/list");
    }

    /**
     * @param idProduto
     * @param model
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada
     * diretamente na URL
     */
    @GetMapping("/edit/{idProduto}")
    public ModelAndView edit(@PathVariable("idProduto") int idProduto, ModelMap model) {
        model.addAttribute("produto", produtorepository.produto(idProduto));
        return new ModelAndView("/produtos/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Produto produto) {
        produtorepository.update(produto);
        return new ModelAndView("redirect:/produtos/list");
    }
}

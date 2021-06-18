package com.jhssantiago.vendas.controller;

import com.jhssantiago.vendas.repository.ClientePFRepository;
import com.jhssantiago.vendas.repository.ItemVendaRepository;
import com.jhssantiago.vendas.repository.ProdutoRepository;
import com.jhssantiago.vendas.repository.VendaRepository;
import com.jhssantiago.vendas.model.ClientePF;
import com.jhssantiago.vendas.model.ItemVenda;
import com.jhssantiago.vendas.model.Usuario;
import com.jhssantiago.vendas.model.Venda;
import com.jhssantiago.vendas.repository.UsuarioRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.transaction.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @Autowired
    ClientePFRepository clientepfrepository;

    @Autowired
    UsuarioRepository usuariorepository;
    
    @Autowired
    ItemVendaRepository ivrepository;
     
    @ResponseBody
    @GetMapping("/teste")
    public String teste() {
        return "Controller Venda Funcionando";
    }

    /**
     * @param itemVenda necessário devido utilizar no form.html o th:object que
     * faz referência ao objeto esperado no controller.
     */
    @GetMapping("/shoppingcart")
    public ModelAndView shoppingcart(ItemVenda itemVenda) { //Carrinho de compras
        ModelMap model = new ModelMap();
        model.addAttribute("clientePF", clientepfrepository.clientesPF()); //mostra lista de clientes
        return new ModelAndView("/vendas/shoppingcart", model);
    }
    @GetMapping("/catalog")
    public ModelAndView catalog(ItemVenda itemVenda) { //Catalogo
        ModelMap model = new ModelMap();
        model.addAttribute("produto", produtorepository.produtos()); //mostra lista de produtos
        model.addAttribute("itemVenda", new ItemVenda());
        return new ModelAndView("/vendas/catalog", model);
    }
    
    @GetMapping("/saleslist")
    public ModelAndView saleslist(Venda venda, ModelMap model) { //lista de vendasData
        model.addAttribute("vendas", vendarepository.vendas());
        return new ModelAndView("/vendas/saleslist", model);
    }
    
    @GetMapping("/details/{idVenda}")
    public ModelAndView details(@PathVariable(value = "idVenda") int idVenda, ModelMap model) { //lista de venda
        
        model.addAttribute("vendas", vendarepository.venda(idVenda));
        model.addAttribute("itemVenda", ivrepository.itemVendas(idVenda));
        return new ModelAndView("/vendas/saledetails", model);
    }
    
    @PostMapping("/add")
    public ModelAndView add(ItemVenda itemVenda, RedirectAttributes attributes) {
        if (itemVenda.getQuantidade() <= 0) {
            attributes.addFlashAttribute("erroQtd", "Quantidade deve ser maior que ou igual à 1");
            return new ModelAndView("redirect:/vendas/catalog");
        }
        itemVenda.setVenda(this.venda);
        itemVenda.setProduto(produtorepository.produto(itemVenda.getProduto().getIdProduto()));
        itemVenda.TotalItem();
        this.venda.setItemVenda(itemVenda);
        this.venda.TotalVenda();
        return new ModelAndView("redirect:/vendas/shoppingcart");
    }

    @PostMapping("/save")
    public ModelAndView save(RedirectAttributes attributes, Venda venda) { //Salvar venda
        if (this.venda.getItemVenda().isEmpty()) {
            attributes.addFlashAttribute("erroItem", "Carrinho está vazio");
        }
        if (!attributes.getFlashAttributes().isEmpty()) {
            return new ModelAndView("redirect:/vendas/shoppingcart");
        }
        this.venda.setId(0);
        ClientePF clientePF = clienteLogado();
        this.venda.setLocalDate(venda.getLocalDate());
        this.venda.setLocalTime(venda.getLocalTime());
        this.venda.TotalVenda();
        this.venda.setCliente(clientepfrepository.clientePF(clientePF.getIdCliente()));
        vendarepository.save(this.venda);
        this.venda.getItemVenda().clear();
        return new ModelAndView("redirect:/vendas/catalog");
    }
    
    public ClientePF clienteLogado(){
        Usuario usuario = usuariorepository.usuario(SecurityContextHolder.getContext().getAuthentication().getName());
        ClientePF clientePF = clientepfrepository.clientePF(usuario);
        return clientePF;
    }

    /**
     * @PathVariable é utilizado quando o valor da variável é passada
     * diretamente na URL
     */
    @GetMapping("/remover/{id}")
    public ModelAndView remover(@PathVariable("id") int id) { //remover item da venda
        for (int i = 0; i < venda.getItemVenda().size(); i++) {
            if (venda.getItemVenda().get(i).getProduto().getIdProduto() == (id)) {
                venda.getItemVenda().remove(i);
            }
        }
        return new ModelAndView("redirect:/vendas/shoppingcart");
    }

    @GetMapping("/buscarfordata")
    public ModelAndView buscarfordata(@RequestParam(value = "databusca") String databusca, ModelMap model) { //buscar por data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate data = LocalDate.parse(databusca, formatter);
        model.addAttribute("vendas", vendarepository.vendasData(data));
        return new ModelAndView("/vendas/saleslist");
    }
    
    @GetMapping("/buscarforname")
    public ModelAndView buscarforname(@RequestParam(value = "nome") String nome, ModelMap model) {
        model.addAttribute("produto", produtorepository.buscarProduto(nome));
        model.addAttribute("itemVenda", new ItemVenda());
        return new ModelAndView("/vendas/catalog");
    }
    
    @GetMapping("/mysales")
    public ModelAndView mysales(Venda venda, ModelMap model) { //lista de vendasData
        ClientePF clientePF = clienteLogado();
        model.addAttribute("vendas", vendarepository.clienteVendas(clientePF));
        return new ModelAndView("/clientes/mysales", model);
    }

}

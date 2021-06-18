package com.jhssantiago.vendas.controller;
import com.jhssantiago.vendas.repository.ClientePFRepository;
import com.jhssantiago.vendas.model.ClientePF;
import com.jhssantiago.vendas.model.Usuario;
import com.jhssantiago.vendas.repository.RoleRepository;
import com.jhssantiago.vendas.repository.UsuarioRepository;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/**
 *
 * @author jhons
 */
@Transactional
@Controller
@RequestMapping("clientes")
public class ControllerClientePF {
    
    @Autowired
    ClientePFRepository clientepfrepository;

    @Autowired
    UsuarioRepository usuariorepository;

    @Autowired
    RoleRepository rolerepository;
 
    /**
     * @param clientePF necessário devido utilizar no form.html o th:object que faz
     * referência ao objeto esperado no controller.
     * @return
     */
    @GetMapping("/form")
    public ModelAndView form(ClientePF clientePF) {
        return new ModelAndView("/clientes/form");
    }
    
    @GetMapping("/formuser")
    public ModelAndView formuser(ClientePF clientePF) {
        return new ModelAndView("/autenticacao/formuser");
    }
    
    @GetMapping("/formadm")
    public ModelAndView formadm(ClientePF clientePF) {
        return new ModelAndView("/clientes/formadm");
    }
    
    public ClientePF insereDadosClientes(ClientePF clientePF){
        Usuario usuario = new Usuario();
        usuario = clientePF.getUsuario();
        usuario.setPassword(new BCryptPasswordEncoder().encode(clientePF.getUsuario().getPassword()));
        usuario.getRoles().add(rolerepository.role(2));
        clientePF.setUsuario(usuario);
        usuario.setCliente(clientePF);
        usuariorepository.save(usuario);
        return clientePF;
    }
    
    @PostMapping("/salvar")
    public ModelAndView salvar(@Valid ClientePF clientePF, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()) {
            attributes.addFlashAttribute("erroCadastro", "Informe Nome e CPF!");
            return formuser(clientePF);           
        }
        if(clientePF.getUsuario().getLogin().equals(null) ||
                clientePF.getUsuario().getLogin().isEmpty() ||
                clientePF.getUsuario().getPassword().equals(null) ||
                clientePF.getUsuario().getPassword().isEmpty()) {
            attributes.addFlashAttribute("erroCadastro", "Informe login e senha!");
            return new ModelAndView("redirect:/clientes/formuser");
        }           
        clientepfrepository.save(insereDadosClientes(clientePF));
        attributes.addFlashAttribute("cadastroUser", "Usuário cadastrado com sucesso!");
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("clientePF", clientepfrepository.clientesPF());
        return new ModelAndView("/clientes/list", model);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid ClientePF clientePF, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()){
            return form(clientePF);
        }
        clientepfrepository.save(insereDadosClientes(clientePF));
        attributes.addFlashAttribute("cadastro", "Usuário cadastrado com sucesso!");
        return new ModelAndView("redirect:/clientes/list");
    }
    
    @PostMapping("/salvaradm")
    public ModelAndView salvaradm(@Valid ClientePF clientePF, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()) {
            return formadm(clientePF);
        }
        if(clientePF.getUsuario().getLogin().equals(null) ||
                clientePF.getUsuario().getLogin().isEmpty() ||
                clientePF.getUsuario().getPassword().equals(null) ||
                clientePF.getUsuario().getPassword().isEmpty()) {

            attributes.addFlashAttribute("erroLogin", "Informe login e senha!");
            return new ModelAndView("redirect:/clientes/formadm");
        } 

        Usuario usuario = new Usuario();
        usuario = clientePF.getUsuario();
        usuario.setPassword(new BCryptPasswordEncoder().encode(clientePF.getUsuario().getPassword()));
        usuario.getRoles().add(rolerepository.role(1));
        clientePF.setUsuario(usuario);
        usuario.setCliente(clientePF);
        usuariorepository.save(usuario);
        clientepfrepository.save(clientePF);
        attributes.addFlashAttribute("cadastro", "Administrador cadastrado com sucesso!");
        return new ModelAndView("redirect:/clientes/list");
    }
    
    public ClientePF clienteLogado(){
        Usuario usuario = usuariorepository.usuario(SecurityContextHolder.getContext().getAuthentication().getName());
        ClientePF clientePF = clientepfrepository.clientePF(usuario);
        return clientePF;
    }
   
    /**
     * @param idCliente
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada
     * diretamente na URL
     */
    @GetMapping("/remove/{idCliente}")
    public ModelAndView remove(@PathVariable("idCliente") int idCliente, RedirectAttributes attributes) {       
        ClientePF cliente = clientepfrepository.clientePF(idCliente);
        if(cliente.getVenda().isEmpty()){
            Usuario usuario = usuariorepository.usuarioCliente(cliente);
            usuariorepository.remove(usuario.getIdUsuario());
            clientepfrepository.remove(idCliente);           
        }else{
            attributes.addFlashAttribute("erroExcluir", "Esse usuário já possui compras");
        }      
        return new ModelAndView("redirect:/clientes/list");
    }

    /**
     * @param id
     * @param model
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada
     * diretamente na URL
     */   
    @GetMapping("/editar")
    public ModelAndView editar(ModelMap model) {
        ClientePF clientePF = clienteLogado();
        int idCliente = clientePF.getIdCliente();
        model.addAttribute("clientePF", clientepfrepository.clientePF(idCliente));       
        return new ModelAndView("/clientes/editaruser", model);
    }

    @PostMapping("/update")
    public ModelAndView update(ClientePF clientePF, RedirectAttributes attributes) {
        if(clientePF.getUsuario().getPassword().isEmpty() || clientePF.getUsuario().getLogin().isEmpty() ){
            attributes.addFlashAttribute("erroAtualizar", "Insiria sua senha!!");
            return new ModelAndView("redirect:/clientes/editar");
        }
        Usuario usuario = usuariorepository.usuarioCliente(clientePF);
        usuario.setPassword(new BCryptPasswordEncoder().encode(clientePF.getUsuario().getPassword()));
        usuario.setLogin(clientePF.getUsuario().getLogin());   
        clientePF.setUsuario(usuariorepository.usuario(usuario.getLogin()));
        usuario.setCliente(clientePF);
        usuariorepository.update(usuario);
        clientepfrepository.update(clientePF);
        attributes.addFlashAttribute("atualizado", "Atualizado com sucesso!!");
        return new ModelAndView("redirect:/clientes/editar");
    }
    
    @GetMapping("/buscarforname")
    public ModelAndView buscarforname(@RequestParam(value = "nome") String nome, ModelMap model) {
        model.addAttribute("clientePF", clientepfrepository.clientes(nome));
        return new ModelAndView("/clientes/list");
    }
}

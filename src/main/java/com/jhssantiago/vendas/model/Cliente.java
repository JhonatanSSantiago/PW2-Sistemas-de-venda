package com.jhssantiago.vendas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;
import javax.validation.constraints.NotBlank;
/**
 *
 * @author jhons
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Cliente implements Serializable{
    
    @Id   
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private int idCliente;
    
    @NotBlank
    private String nome;
    
    @OneToMany(mappedBy = "cliente")
    private List<Venda> venda = new ArrayList<>();
    
    @JoinColumn(name = "id_usuario")
    @OneToOne(cascade = CascadeType.PERSIST)
    private Usuario usuario;
    
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
     public List<Venda> getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda.add(venda);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}

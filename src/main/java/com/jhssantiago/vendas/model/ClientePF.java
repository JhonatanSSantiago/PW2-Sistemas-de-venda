package com.jhssantiago.vendas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 *
 * @author jhons
 */
@Entity
@Table(name = "tb_clientepf")
public class ClientePF  extends Cliente implements Serializable{
    
    private String nome;
    private String CPF;

    @OneToMany(mappedBy = "cliente")
    private List<Venda> venda = new ArrayList<>();
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
    
        public List<Venda> getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda.add(venda);
    }
    
}

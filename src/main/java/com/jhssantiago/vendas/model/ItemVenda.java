package com.jhssantiago.vendas.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.*;

/**
 *
 * @author jhons
 */
@Entity
@Table(name = "tb_itemVenda")
public class ItemVenda implements Serializable {

    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int idItemVenda;
    
    @Min(1)
    private int quantidade;

    @OneToOne(cascade = CascadeType.MERGE)
    private Produto produto;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Venda venda;

    public int getIdItemVenda() {
        return idItemVenda;
    }

    public void setId(int idItemVenda) {
        this.idItemVenda = idItemVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {    
        this.venda = venda;
    }
    
    public double TotalItem() {
        return this.produto.getValor() * this.quantidade;
    }

 /*   @Override
    public String toString() {
        return " " + quantidade + " - " + produto + ' ';
    } */

}

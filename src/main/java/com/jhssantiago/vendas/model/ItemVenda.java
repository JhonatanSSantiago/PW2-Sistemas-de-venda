package com.jhssantiago.vendas.model;

import com.jhssantiago.vendas.model.Venda;
import com.jhssantiago.vendas.model.Produto;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author jhons
 */
@Entity
@Table(name = "tb_itemvenda") //pessoa
public class ItemVenda implements Serializable {
    
    @Id
    @GeneratedValue
    private int idItem;
    
    private double quantidade;

    @OneToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_venda")
    private Venda venda;

    public int getIdItem() {
        return idItem;
    }

    public void setId(int idItem) {
        this.idItem = idItem;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public double TotalItem() {
        return this.produto.getValor() * this.quantidade;
    }
    
    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

}

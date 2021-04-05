package com.jhssantiago.vendas.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jhons
 */
@Entity
@Table(name = "tb_produtos") //endereco
public class Produto implements Serializable {

    @Id
    @GeneratedValue
    private int idProduto;

    private String descricao;

    private double valor;

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return " " + descricao + " - Valor Unitário: " + valor + ' ';
    }

}

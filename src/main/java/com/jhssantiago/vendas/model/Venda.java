/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhssantiago.vendas.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jhons
 */
@Entity
@Table(name = "tb_venda") //pessoa
public class Venda implements Serializable {

    @Id
    @GeneratedValue
    private int idVenda;

    private Date data;

    @OneToMany(mappedBy = "venda")
    private List<ItemVenda> itemVenda;

    public int getIdVenda() {
        return idVenda;
    }

    public void setId(int idVenda) {
        this.idVenda = idVenda;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<ItemVenda> getItemVenda() {
        return itemVenda;
    }

    public void setItemVenda(List<ItemVenda> itemVenda) {
        this.itemVenda = itemVenda;
    }

    public double QtdTotalItem() {
        double QtdTotal = 0;
        for (ItemVenda i : itemVenda) {
            QtdTotal = QtdTotal+i.getQuantidade();
        }
        return QtdTotal;
    }

    public double TotalVenda() {
        double Total = 0;
        for (ItemVenda i : itemVenda) {
            Total = Total+i.TotalItem();
        }
        return Total;
    }

}

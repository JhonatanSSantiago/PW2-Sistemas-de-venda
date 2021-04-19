package com.jhssantiago.vendas.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author jhons
 */
@Scope("session")
@Component
@Entity
public class Venda implements Serializable {

   
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int idVenda;

    private LocalDate localDate = LocalDate.now();

    @OneToMany(mappedBy = "venda", cascade = CascadeType.PERSIST)
    private List<ItemVenda> itemVenda = new ArrayList<>();

    public int getIdVenda() {
        return idVenda;
    }

    public void setId(int idVenda) {
        this.idVenda = idVenda;
    }

     public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public List<ItemVenda> getItemVenda() {
        return itemVenda;
    }

    public void setItemVenda(ItemVenda itemVenda) {
        this.itemVenda.add(itemVenda);
    }
    
    public double TotalVenda() {
        double Total = 0;
        for (ItemVenda i : itemVenda) {
            Total += i.TotalItem();
        }
        return Total;
    }

 /*   @Override
    public String toString() {
        return "" + itemVenda + ' ';
    } */

}

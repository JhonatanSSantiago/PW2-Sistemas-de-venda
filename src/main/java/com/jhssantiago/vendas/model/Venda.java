package com.jhssantiago.vendas.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.text.DecimalFormat; 
import java.time.LocalTime;

/**
 *
 * @author jhons
 */
@Entity
@Scope("session")
@Component
public class Venda implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int idVenda;

    private LocalDate localDate = LocalDate.now();
    
    private LocalTime localTime = LocalTime.now();   

    @ManyToOne
    private ClientePF cliente;

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

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }
 
    public List<ItemVenda> getItemVenda() {
        return itemVenda;
    }

    public void setItemVenda(ItemVenda itemVenda) {
        this.itemVenda.add(itemVenda);
    }

    public ClientePF getCliente() {
        return cliente;
    }

    public void setCliente(ClientePF cliente) {
        this.cliente = cliente;
    }

    public String TotalVenda() {
        double Total = 0;
        DecimalFormat df = new DecimalFormat("#,###.00");      
        for (ItemVenda i : itemVenda) {
            Total += i.TotalItem();
        }
        return df.format(Total);
    }
    
    public int QtdTotalItem() {
        int QtdTotal = 0;
        for (ItemVenda i : itemVenda) {
            QtdTotal = QtdTotal+i.getQuantidade();
        }
        return QtdTotal;
    }

}

package com.jhssantiago.vendas.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author jhons
 */
@Entity
@Table(name = "tb_clientepf")
public class ClientePF extends Cliente implements Serializable {


    @NotBlank
    private String CPF;

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

}

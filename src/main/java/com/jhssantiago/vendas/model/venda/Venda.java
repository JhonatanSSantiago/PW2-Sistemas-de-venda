/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhssantiago.vendas.model.venda;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jhons
 */
@Entity
@Table(name = "tb_venda")
public class Venda implements Serializable {
    
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String nome;
    private int idade;
}

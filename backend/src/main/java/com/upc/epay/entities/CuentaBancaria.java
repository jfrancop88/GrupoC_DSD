package com.upc.epay.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CuentaBancaria")
public class CuentaBancaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuentaBancaria;
    @ManyToOne
    private Banco idbanco;

    @ManyToOne
    private Moneda idmoneda;

    @Column(length = 45)
    private String nuCuenta;

    public Long getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public void setIdCuentaBancaria(Long idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }

    public Banco getIdbanco() {
        return idbanco;
    }

    public void setIdbanco(Banco idbanco) {
        this.idbanco = idbanco;
    }

    public Moneda getIdmoneda() {
        return idmoneda;
    }

    public void setIdmoneda(Moneda idmoneda) {
        this.idmoneda = idmoneda;
    }

    public String getNuCuenta() {
        return nuCuenta;
    }

    public void setNuCuenta(String nuCuenta) {
        this.nuCuenta = nuCuenta;
    }
}

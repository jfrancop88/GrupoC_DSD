package com.upc.epay.entities;

import javax.persistence.*;

@Entity
@Table(name="Comercio")
public class Comercio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComercio;
    @Column(length = 11)
    private String ruc;
    @Column(length = 150)
    private String razonSocial;

    @ManyToOne
    private CuentaBancaria idCuenta;

    public Long getIdComercio() {
        return idComercio;
    }

    public void setIdComercio(Long idComercio) {
        this.idComercio = idComercio;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public CuentaBancaria getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(CuentaBancaria idCuenta) {
        this.idCuenta = idCuenta;
    }
}

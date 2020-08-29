package com.upc.epay.entities;

import javax.persistence.*;

@Entity
@Table(name="Transacciones")
public class Transacciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransacciones;

    @ManyToOne
    private Comercio idComercio;

    @Column(length = 45)
    private String customer;
    @Column(length = 45)
    private String monto;
    @Column(length = 150)
    private String descripcion;

    public Long getIdTransacciones() {
        return idTransacciones;
    }

    public void setIdTransacciones(Long idTransacciones) {
        this.idTransacciones = idTransacciones;
    }

    public Comercio getIdComercio() {
        return idComercio;
    }

    public void setIdComercio(Comercio idComercio) {
        this.idComercio = idComercio;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

package com.upc.epay.entities;

import javax.persistence.*;

@Entity
@Table(name="Moneda")
public class Moneda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMoneda;
    @Column(length = 3)
    private String codigo;
    @Column(length = 45)
    private String descripción;

    public Long getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(Long idMoneda) {
        this.idMoneda = idMoneda;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }
}

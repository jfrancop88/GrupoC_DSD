package com.upc.epay.entities;

import javax.persistence.*;

@Entity
@Table(name="OpFinanciera")
public class OpFinanciera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOpFinanciera;

    @ManyToOne
    private Transacciones idTransacciones;

    @Column(length = 150)
    private String noTitular;
    @Column(length = 16)
    private Integer nuTarjeta;
    @Column(length = 8)
    private Integer nuDNI;
    @Column(length = 3)
    private Integer CCV;
    @Column(length = 2)
    private Integer mesVencimiento;
    @Column(length = 4)
    private Integer anoVencimiento;
    @Column(length = 2)
    private Integer nuCuotas;
    @Column(length = 9)
    private Integer nuCelular;

    public Long getIdOpFinanciera() {
        return idOpFinanciera;
    }

    public void setIdOpFinanciera(Long idOpFinanciera) {
        this.idOpFinanciera = idOpFinanciera;
    }

    public Transacciones getIdTransacciones() {
        return idTransacciones;
    }

    public void setIdTransacciones(Transacciones idTransacciones) {
        this.idTransacciones = idTransacciones;
    }

    public String getNoTitular() {
        return noTitular;
    }

    public void setNoTitular(String noTitular) {
        this.noTitular = noTitular;
    }

    public Integer getNuTarjeta() {
        return nuTarjeta;
    }

    public void setNuTarjeta(Integer nuTarjeta) {
        this.nuTarjeta = nuTarjeta;
    }

    public Integer getNuDNI() {
        return nuDNI;
    }

    public void setNuDNI(Integer nuDNI) {
        this.nuDNI = nuDNI;
    }

    public Integer getCCV() {
        return CCV;
    }

    public void setCCV(Integer CCV) {
        this.CCV = CCV;
    }

    public Integer getMesVencimiento() {
        return mesVencimiento;
    }

    public void setMesVencimiento(Integer mesVencimiento) {
        this.mesVencimiento = mesVencimiento;
    }

    public Integer getAnoVencimiento() {
        return anoVencimiento;
    }

    public void setAnoVencimiento(Integer anoVencimiento) {
        this.anoVencimiento = anoVencimiento;
    }

    public Integer getNuCuotas() {
        return nuCuotas;
    }

    public void setNuCuotas(Integer nuCuotas) {
        this.nuCuotas = nuCuotas;
    }

    public Integer getNuCelular() {
        return nuCelular;
    }

    public void setNuCelular(Integer nuCelular) {
        this.nuCelular = nuCelular;
    }
}

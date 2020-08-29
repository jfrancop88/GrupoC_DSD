package com.upc.epay.entities;
import javax.persistence.*;

@Entity
@Table(name ="banco")
public class Banco {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // Afecta a la primera línea @Id crea la llave primaria @GenerateValue para autogenerar código
    private Long idBanco;
    @Column(length = 150, nullable = false) //Afecta a la columna que se menciona en la siguiente línea
    private String noBanco;

    public Long getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Long idBanco) {
        this.idBanco = idBanco;
    }

    public String getNoBanco() {
        return noBanco;
    }

    public void setNoBanco(String noBanco) {
        this.noBanco = noBanco;
    }
}

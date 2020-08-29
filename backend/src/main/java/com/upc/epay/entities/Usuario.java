package com.upc.epay.entities;

import javax.persistence.*;

@Entity
@Table(name="Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @ManyToOne
    private Comercio idComercio;

    @Column(length = 16)
    private String username;
    @Column(length = 255)
    private String email;
    private String password;
}

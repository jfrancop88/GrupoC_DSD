package com.upc.epay.controllers;

import com.upc.epay.entities.Banco;
import com.upc.epay.repository.BancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BancoController {

    @Autowired
    private BancoRepository bancoRepository;

    public Banco crearBanco(Banco banco){
        Banco a;
        return a = bancoRepository.save(banco);
    }
}


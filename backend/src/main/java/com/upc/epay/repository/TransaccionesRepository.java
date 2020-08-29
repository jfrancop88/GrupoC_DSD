package com.upc.epay.repository;

import com.upc.epay.entities.Transacciones;
import org.springframework.data.repository.CrudRepository;

public interface TransaccionesRepository extends CrudRepository<Transacciones, Long> {
}

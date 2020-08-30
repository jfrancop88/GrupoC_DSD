package org.upc.project.examplerest.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {

    List<T> findAll();

    Optional<T> findById(Long id);

    T save(T t) throws Exception;

    T update(T t);

    T delete(Long id);
}

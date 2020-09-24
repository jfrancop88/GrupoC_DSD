package org.upc.project.payment.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {

    List<T> findAll();

    T findById(String id);

    T save(T t) throws Exception;

    T update(T t) throws Exception;

    T delete(String id) throws Exception;
}

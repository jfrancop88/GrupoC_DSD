package org.upc.project.examplerest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upc.project.examplerest.entity.Customer;
import org.upc.project.examplerest.entity.Ecommerce;

@Repository
public interface EcommerceRepository extends CrudRepository<Ecommerce, Long> {
    Ecommerce findByEcommerceCode(String ecommerceCode);
}

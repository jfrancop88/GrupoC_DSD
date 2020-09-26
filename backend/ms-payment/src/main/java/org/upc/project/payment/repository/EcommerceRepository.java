package org.upc.project.payment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upc.project.payment.entity.Ecommerce;

@Repository
public interface EcommerceRepository extends CrudRepository<Ecommerce, Long> {
    Ecommerce findByEcommerceCode(String ecommerceCode);
}

package org.upc.project.examplerest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upc.project.examplerest.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}

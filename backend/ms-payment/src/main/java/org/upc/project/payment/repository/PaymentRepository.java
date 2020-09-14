package org.upc.project.payment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upc.project.payment.entity.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {


}

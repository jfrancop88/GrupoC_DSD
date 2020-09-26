package org.upc.project.payment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upc.project.payment.entity.Customer;
import org.upc.project.payment.entity.Payment;

import java.util.Date;
import java.util.List;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {
    Payment findByTransactionNumber(String transactionNumber);

    List<Payment> findAllByTransactionNumber(String transactionNumber);

    List<Payment> findAllByPaymentDateBetween(Date now, Date paymentDate);

    List<Payment> findAllByCustomer(Customer customer);
}

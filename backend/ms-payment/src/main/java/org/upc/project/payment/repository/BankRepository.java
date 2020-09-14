package org.upc.project.payment.repository;

import org.springframework.data.repository.CrudRepository;
import org.upc.project.payment.entity.Bank;

public interface BankRepository extends CrudRepository<Bank, Long> {

    Bank findByBankCode(String bankCode);

    Bank findByBinNumber(String binNumber);
}

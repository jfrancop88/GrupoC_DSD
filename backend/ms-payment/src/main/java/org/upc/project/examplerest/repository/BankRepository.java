package org.upc.project.examplerest.repository;

import org.springframework.data.repository.CrudRepository;
import org.upc.project.examplerest.entity.Bank;

public interface BankRepository extends CrudRepository<Bank, Long> {

    Bank findByBankCode(String bankCode);

    Bank findByBinNumber(String binNumber);
}

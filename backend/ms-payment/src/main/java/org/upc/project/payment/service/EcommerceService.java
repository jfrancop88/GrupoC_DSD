package org.upc.project.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upc.project.payment.beans.EcommerceDTO;
import org.upc.project.payment.entity.Ecommerce;
import org.upc.project.payment.entity.Payment;
import org.upc.project.payment.repository.EcommerceRepository;

import java.util.List;
@Service
public class EcommerceService implements GenericService<EcommerceDTO>{
    @Autowired
    private EcommerceRepository ecommerceRepository;

    @Override
    public List<EcommerceDTO> findAll() {
        return null;
    }

    @Override
    public EcommerceDTO findById(String id) {
        return null;
    }

    @Override
    public EcommerceDTO save(EcommerceDTO ecommerceDTO){


        Ecommerce ecommerce = Ecommerce.builder()
                .name(ecommerceDTO.getName())
                .ecommerceCode("code_test")
                .commissionPercentage(5)
                .enabled(true)
                .accountNumber(ecommerceDTO.getAccountNumber())
                .build();

        Ecommerce ecommerceCreate = ecommerceRepository.save(ecommerce);

        return ecommerceDTO;
    }

    @Override
    public EcommerceDTO update(EcommerceDTO ecommerceDTO) {
        return null;
    }

    @Override
    public EcommerceDTO delete(Long id) {
        return null;
    }

}

package org.upc.project.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upc.project.payment.beans.EcommerceDTO;
import org.upc.project.payment.entity.Ecommerce;
import org.upc.project.payment.repository.EcommerceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EcommerceService implements GenericService<EcommerceDTO> {
    @Autowired
    private EcommerceRepository ecommerceRepository;

    @Override
    public List<EcommerceDTO> findAll() {
        List<EcommerceDTO> ecommerceDTOS = new ArrayList<>();
        List<Ecommerce> ecommerces = (List<Ecommerce>) ecommerceRepository.findAll();

        ecommerces.forEach(ecommerce -> ecommerceDTOS.add(EcommerceDTO.builder()
                .code(ecommerce.getEcommerceCode())
                .name(ecommerce.getName())
                .percentage(ecommerce.getCommissionPercentage())
                .accountNumber(ecommerce.getAccountNumber())
                .build()));
        return ecommerceDTOS;
    }

    @Override
    public EcommerceDTO findById(String id) {
        Ecommerce ecommerce = ecommerceRepository.findByEcommerceCode(id);

        if (Objects.nonNull(ecommerce)) {
            return EcommerceDTO.builder()
                    .code(ecommerce.getEcommerceCode())
                    .name(ecommerce.getName())
                    .percentage(ecommerce.getCommissionPercentage())
                    .accountNumber(ecommerce.getAccountNumber())
                    .build();
        }
        return null;
    }

    @Override
    public EcommerceDTO save(EcommerceDTO ecommerceDTO) throws Exception {

        Ecommerce ecommerceFound = ecommerceRepository.findByEcommerceCode(ecommerceDTO.getCode());
        if (Objects.nonNull(ecommerceFound)) {
            throw new Exception("Ya existe Ecommerce");
        }

        Ecommerce ecommerce = Ecommerce.builder()
                .name(ecommerceDTO.getName())
                .ecommerceCode(ecommerceDTO.getCode())
                .commissionPercentage(ecommerceDTO.getPercentage())
                .enabled(true)
                .accountNumber(ecommerceDTO.getAccountNumber())
                .build();

        ecommerceRepository.save(ecommerce);

        return ecommerceDTO;
    }

    @Override
    public EcommerceDTO update(EcommerceDTO ecommerceDTO) throws Exception {
        Ecommerce ecommerceFound = ecommerceRepository.findByEcommerceCode(ecommerceDTO.getCode());
        if (Objects.isNull(ecommerceFound)) {
            throw new Exception("No existe Ecommerce");
        }
        ecommerceFound.setCommissionPercentage(ecommerceDTO.getPercentage());
        ecommerceFound.setName(ecommerceDTO.getName());
        ecommerceFound.setAccountNumber(ecommerceDTO.getAccountNumber());
        ecommerceRepository.save(ecommerceFound);
        return ecommerceDTO;
    }

    @Override
    public EcommerceDTO delete(String id) throws Exception {
        Ecommerce ecommerceFound = ecommerceRepository.findByEcommerceCode(id);
        if (Objects.isNull(ecommerceFound)) {
            throw new Exception("No existe Ecommerce");
        }
        ecommerceRepository.delete(ecommerceFound);
        return null;
    }

}

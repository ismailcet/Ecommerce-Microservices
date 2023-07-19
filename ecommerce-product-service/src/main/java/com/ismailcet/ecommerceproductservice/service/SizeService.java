package com.ismailcet.ecommerceproductservice.service;

import com.ismailcet.ecommerceproductservice.repository.SizeRepository;
import org.springframework.stereotype.Service;

@Service
public class SizeService {
    private final SizeRepository sizeRepository;

    public SizeService(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }
}

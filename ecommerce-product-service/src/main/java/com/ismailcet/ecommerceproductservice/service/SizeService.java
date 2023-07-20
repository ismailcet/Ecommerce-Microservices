package com.ismailcet.ecommerceproductservice.service;

import com.ismailcet.ecommerceproductservice.dto.converter.SizeDtoConverter;
import com.ismailcet.ecommerceproductservice.dto.request.CreateSizeRequest;
import com.ismailcet.ecommerceproductservice.dto.response.SizeDto;
import com.ismailcet.ecommerceproductservice.entity.Size;
import com.ismailcet.ecommerceproductservice.repository.SizeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SizeService {
    private final SizeRepository sizeRepository;

    public SizeService(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }
    public SizeDto createSize(CreateSizeRequest createSizeRequest) {
        Size size = Size.builder()
                .name(createSizeRequest.getName())
                .build();

        sizeRepository.save(size);
        return SizeDtoConverter.converter(size);
    }

    public void deleteSizeById(Integer id) {
        Size size =
                sizeRepository.findById(id).orElseThrow(()-> new NullPointerException("Size Id does not exist ! "));

        sizeRepository.deleteById(id);
    }

    public List<SizeDto> getAllSizes() {
        return sizeRepository.findAll()
                .stream()
                .map(SizeDtoConverter::converter)
                .collect(Collectors.toList());
    }
}

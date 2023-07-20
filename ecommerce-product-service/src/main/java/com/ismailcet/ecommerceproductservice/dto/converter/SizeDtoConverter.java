package com.ismailcet.ecommerceproductservice.dto.converter;
import com.ismailcet.ecommerceproductservice.dto.response.SizeDto;
import com.ismailcet.ecommerceproductservice.entity.Size;
import org.springframework.stereotype.Component;

@Component
public class SizeDtoConverter {
    public static SizeDto converter(Size size){
        return SizeDto.builder()
                .id(size.getId())
                .name(size.getName())
                .build();
    }
}

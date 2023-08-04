package com.ismailcet.ecommerceorderservice.client;

import com.ismailcet.ecommerceorderservice.dto.response.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", path = "v1/product")
public interface ProductServiceClient {
    @GetMapping("/{productId}")
    ResponseEntity<ProductDto> getProductByProductId(@PathVariable Integer productId);
}

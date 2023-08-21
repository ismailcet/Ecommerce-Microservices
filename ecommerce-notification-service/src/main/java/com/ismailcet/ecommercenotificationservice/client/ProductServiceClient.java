package com.ismailcet.ecommercenotificationservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service", path = "v1/product")
public interface ProductServiceClient {
    @GetMapping("/{productId}/stock/{quantity}")
    ResponseEntity<String> decreaseStockByProductId(@PathVariable("productId") Integer id,@PathVariable("quantity") Integer quantity);
}

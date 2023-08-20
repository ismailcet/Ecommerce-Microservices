package com.ismailcet.ecommercenotificationservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service", path = "v1/product")
public interface ProductServiceClient {
    @PutMapping("/stock/${productId}")
    public ResponseEntity<String> decreaseStockByProductId(@PathVariable Integer id, @RequestBody Integer number);
}

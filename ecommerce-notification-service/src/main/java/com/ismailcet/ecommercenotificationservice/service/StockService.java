package com.ismailcet.ecommercenotificationservice.service;

import com.ismailcet.ecommercenotificationservice.client.ProductServiceClient;
import com.ismailcet.ecommercenotificationservice.exception.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StockService {
    private final ProductServiceClient productServiceClient;
    private final Logger logger = LoggerFactory.getLogger(StockService.class);

    public StockService(ProductServiceClient productServiceClient) {
        this.productServiceClient = productServiceClient;
    }

    public void decreaseStock(Map<Integer, Integer> productIdList) {
        productIdList.entrySet()
                .stream()
                .map(product->{
                    ResponseEntity<String> response =
                            productServiceClient.decreaseStockByProductId(product.getKey(), product.getValue());
                    return response.getStatusCodeValue();
                }).forEach(code -> System.out.println(code));
        logger.info("Product Stocks are decreased" + productIdList.entrySet());
    }
}

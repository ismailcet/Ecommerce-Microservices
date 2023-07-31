package com.ismailcet.ecommerceproductservice.controller;
import com.ismailcet.ecommerceproductservice.dto.request.CreateSizeRequest;
import com.ismailcet.ecommerceproductservice.dto.response.SizeDto;
import com.ismailcet.ecommerceproductservice.service.SizeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/sizes")
public class SizeController {
    private final SizeService sizeService;

    public SizeController(SizeService sizeService) {
        this.sizeService = sizeService;
    }
    @PostMapping()
    public ResponseEntity<SizeDto> createSize(@RequestBody CreateSizeRequest createSizeRequest){
        return new ResponseEntity<>(
                sizeService.createSize(createSizeRequest),
                HttpStatus.CREATED
        );
    }
    @DeleteMapping("/{sizeId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteSizeById(@PathVariable("sizeId") Integer id){
        sizeService.deleteSizeById(id);
    }
    @GetMapping()
    public ResponseEntity<List<SizeDto>> getAllSizes(){
        return ResponseEntity.ok(sizeService.getAllSizes());
    }
}

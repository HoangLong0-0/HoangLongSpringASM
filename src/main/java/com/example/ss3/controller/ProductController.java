package com.example.ss3.controller;

import com.example.ss3.dto.ProductDto;
import com.example.ss3.entity.ProductEntity;
import com.example.ss3.model.BaseResponse;
import com.example.ss3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping()
    public ResponseEntity getAllProduct(@RequestParam(value = "name", required = false, defaultValue = "Nokia cucgach") String name,
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                        @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {
        BaseResponse res = new BaseResponse();
        res.data = productService.getAllProduct(name, PageRequest.of(page, limit));
        return ResponseEntity.ok(res);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody ProductDto productDto) {
        ProductEntity entity = new ProductEntity();
//        entity.setName(productDto.getName());
//        entity.setPrice(productDto.getPrice());

        return null;
    }

}

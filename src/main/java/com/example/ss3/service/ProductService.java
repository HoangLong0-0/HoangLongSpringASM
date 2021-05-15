package com.example.ss3.service;

import com.example.ss3.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<ProductEntity> getAllProduct();
    List<ProductEntity> getAllProduct(String name, Pageable pageable);
    ProductEntity addProduct(ProductEntity p);
    void deleteProduct(Integer productid);
    void updateProduct(Integer id, String name, Integer price, Integer quantity, Integer category);
    Page<ProductEntity> findPaginated(int pageNo, int pageSize);
}

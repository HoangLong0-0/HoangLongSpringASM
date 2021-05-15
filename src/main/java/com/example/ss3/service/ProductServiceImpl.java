package com.example.ss3.service;

import com.example.ss3.entity.ProductEntity;
import com.example.ss3.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    int pageSize = 5;
    @Autowired
    ProductRepo productRepo;

    @Override
    public List<ProductEntity> getAllProduct() {

        return productRepo.findAll();
    }

    @Override
    public List<ProductEntity> getAllProduct(String name, Pageable pageable) {
        return productRepo.findAllByName(name, pageable);
    }

    @Override
    public ProductEntity addProduct(ProductEntity p) {
        ProductEntity product = productRepo.save(p);
        return product;
    }

    @Override
    public void deleteProduct(Integer productid) {
        ProductEntity p = productRepo.findById(productid).get();
        productRepo.delete(p);
    }

    @Override
    public void updateProduct(Integer id, String name, Integer price, Integer quantity, Integer category) {
       ProductEntity p = productRepo.findById(id).get();
        if(!StringUtils.isEmpty(name))
        {
            p.setName(name);
        }
        if(price!=null)
        {
            p.setPrice(price);
        }
        if(quantity!=null)
        {
            p.setQuantity(quantity);
        }
        if(category!=null)
        {
            p.setCategoryid(category);
        }
       productRepo.save(p);
    }

    @Override
    public Page<ProductEntity> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.productRepo.findAll(pageable);
    }
//    int pageNumber = 1;
//    Pageable pageable = PageRequest.of(pageNumber, pageSize);
//    Page<ProductEntity> page = productRepo.findAll(pageable);


}

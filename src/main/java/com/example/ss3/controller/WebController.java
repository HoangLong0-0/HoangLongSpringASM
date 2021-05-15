package com.example.ss3.controller;

import com.example.ss3.entity.ProductEntity;
import com.example.ss3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;
import sun.reflect.generics.scope.Scope;

import java.util.List;
import java.util.Map;

@Controller
public class WebController {
    @Autowired
    ProductService productService;
    @GetMapping("/")
    public String index(Model model) {
        List<ProductEntity> productList = productService.getAllProduct();
        model.addAttribute("productList", productList);


        return findPaginated(1, model);
    }
    @GetMapping("/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;

        Page< ProductEntity > page = productService.findPaginated(pageNo, pageSize);
        List < ProductEntity > listProducts = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("productList", listProducts);
        return "index";
    }

    @PostMapping("/create")
    public String create(Model model, @RequestParam Map<String, String> params){
        String name = params.get("name");
        Integer price = Integer.valueOf(params.get("price"));
        Integer quantity = Integer.valueOf(params.get("quantity"));
        Integer category = Integer.valueOf(params.get("category"));
        ProductEntity p = new ProductEntity(category,name,price,quantity);
        productService.addProduct(p);
        String result = "successfully added!";
        model.addAttribute("result",result);
        return "result";
    }

    @GetMapping("/delete")
    public String delete(Model model,  @RequestParam Map<String, String> params){
        Integer id = Integer.valueOf(params.get("deleteid"));
        productService.deleteProduct(id);
        String result = "successfully deleted!";
        model.addAttribute("result",result);
        return "result";
    }

    @GetMapping("/update")
    public String update(Model model,  @RequestParam Map<String, String> params){
        Integer id = Integer.valueOf(params.get("updateid"));
        String name = params.get("name");
        Integer price = checknull("price",params);
        Integer quantity = checknull("quantity",params);
        Integer category = checknull("category",params);

//        Integer quantity = Integer.valueOf(params.get("quantity"));
//        Integer category = Integer.valueOf(params.get("category"));

        productService.updateProduct(id,name,price,quantity,category);
        String result = "successfully updated!";
        model.addAttribute("result",result);
        return "result";

    }

    Integer checknull(String paramname, Map<String, String> params ){
        String value = params.get(paramname);
        if(!value.isEmpty()){
            return  Integer.valueOf(value);
        }
        return null;
    }

}

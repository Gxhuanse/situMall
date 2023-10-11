package com.gxh.controller.api;

import com.gxh.common.ResponseBean;
import com.gxh.entity.Category;
import com.gxh.entity.Product;
import com.gxh.service.CategoryService;
import com.gxh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductApiController {
    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseBean selectById(Product product){
        ResponseBean responseBean=null;
        try {
//            List<Product> productList= productService.selectByID(id);
            List<Product> productList= productService.search(product);
            responseBean=ResponseBean.ok(productList);
        } catch (Exception e) {
            responseBean=ResponseBean.failed("查询失败err");
            throw new RuntimeException(e);
        }
        return responseBean;
    }

}

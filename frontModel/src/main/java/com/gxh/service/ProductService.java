package com.gxh.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gxh.entity.Category;
import com.gxh.entity.Product;

import java.util.List;

public interface ProductService extends IService<Product> {
    List<Product> selectByID(Integer id);

    List<Product> search(Product product);

    IPage<Product> listPage(Integer page, Integer limit, Product product);
}

package com.gxh.dao;

import com.gxh.entity.Product;
import com.gxh.entity.dto.PageDTO;
import com.gxh.entity.dto.product.ProductAddInDTO;
import com.gxh.entity.dto.product.ProductSelectPageConditionInDTO;

public interface ProductDao {
    PageDTO selectProductByPageCondition(ProductSelectPageConditionInDTO dto);

    int updataStatus(Product product);

    int productAdd(ProductAddInDTO dto);

    int updataProduct(Product product);

    int deleteProduct(Integer id);
}

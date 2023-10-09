package com.gxh.service;

import com.gxh.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gxh.entity.dto.PageDTO;
import com.gxh.entity.dto.product.ProductAddInDTO;
import com.gxh.entity.dto.product.ProductSelectPageConditionInDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gxhuanse
 * @since 2023-09-27
 */
public interface IProductService extends IService<Product> {
    PageDTO selectProductByPageCondition(ProductSelectPageConditionInDTO dto);

    int updataStatus(Product product);

    int productAdd(ProductAddInDTO dto);

    int productEdit(ProductAddInDTO inDTO);

    int productDelete(Integer id);
}

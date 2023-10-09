package com.gxh.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxh.dao.ProductDao;
import com.gxh.entity.Product;
import com.gxh.entity.dto.PageDTO;
import com.gxh.entity.dto.product.ProductAddInDTO;
import com.gxh.entity.dto.product.ProductSelectPageConditionInDTO;
import com.gxh.entity.dto.product.ProductSelectPageConditionOutDTO;
import com.gxh.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    ProductMapper productMapper;

    @Override
    public PageDTO selectProductByPageCondition(ProductSelectPageConditionInDTO dto) {
        PageDTO pageDto=new PageDTO();

        PageHelper.startPage(dto.getCurr(),dto.getNums());

        List<ProductSelectPageConditionOutDTO> dtoList = productMapper.selectProductByPageCondition(dto);

        PageInfo<ProductSelectPageConditionOutDTO> pageInfo=new PageInfo<>(dtoList);

        pageDto.setCount(pageInfo.getTotal());
        pageDto.setList(pageInfo.getList());
        return pageDto;
    }

    @Override
    public int updataStatus(Product product) {
        LambdaQueryWrapper<Product> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Product::getId,product.getId());
        return productMapper.update(product,queryWrapper);
    }

    @Override
    public int productAdd(ProductAddInDTO dto) {
        return productMapper.insert(dto);
    }

    @Override
    public int updataProduct(Product product) {
        return productMapper.updateById(product);
    }

    @Override
    public int deleteProduct(Integer id) {
        return productMapper.deleteById(id);
    }
}

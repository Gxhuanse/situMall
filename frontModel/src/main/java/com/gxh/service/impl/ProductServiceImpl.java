package com.gxh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxh.entity.Category;
import com.gxh.entity.Product;
import com.gxh.entity.ProductPic;
import com.gxh.mapper.CategoryMapper;
import com.gxh.mapper.ProductMapper;
import com.gxh.mapper.ProductPicMapper;
import com.gxh.service.CategoryService;
import com.gxh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductPicMapper picMapper;

    @Override
    public List<Product> selectByID(Integer id) {
        QueryWrapper<Product>  queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(Product::getStatus,1)
                .eq(Product::getCategoryId,id);
        List<Product> productList = productMapper.selectList(queryWrapper);
        if (productList!=null&& !productList.isEmpty()){
            for (Product product : productList) {
                QueryWrapper<ProductPic> queryWrapper1=new QueryWrapper<>();
                queryWrapper1.lambda().eq(ProductPic::getProductId,product.getId());
                List<ProductPic> list = picMapper.selectList(queryWrapper1);
                if (list!=null&&!list.isEmpty()){
                    List<String>  pics=new ArrayList<>();
                    for (ProductPic productPic : list) {
                        pics.add(productPic.getUrl());
                    }
                    product.setPics(pics);
                }
            }
        }
        return productList;
    }

    @Override
    public List<Product> search(Product pt) {
        LambdaQueryWrapper<Product>  queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Product::getStatus,1)
                .eq(pt.getId()!=null,Product::getId,pt.getId())
                .eq(pt.getCategoryId()!=null,Product::getCategoryId,pt.getCategoryId())
                .eq(pt.getRecom()!=null,Product::getRecom,pt.getRecom())
                .like(pt.getProductName()!=null,Product::getProductName,pt.getProductName());

        List<Product> productList = productMapper.selectList(queryWrapper);
        if (productList!=null&& !productList.isEmpty()){
            for (Product product : productList) {
                QueryWrapper<ProductPic> queryWrapper1=new QueryWrapper<>();
                queryWrapper1.lambda().eq(ProductPic::getProductId,product.getId());
                List<ProductPic> list = picMapper.selectList(queryWrapper1);
                if (list!=null&&!list.isEmpty()){
                    List<String>  pics=new ArrayList<>();
                    for (ProductPic productPic : list) {
                        pics.add(productPic.getUrl());
                    }
                    product.setPics(pics);
                }
            }
        }
        return productList;
    }
}

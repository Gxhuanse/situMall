package com.gxh.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gxh.dao.ProductPicDao;
import com.gxh.entity.ProductPic;
import com.gxh.mapper.ProductPicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductPicDaoImpl implements ProductPicDao {
    @Autowired
    ProductPicMapper productPicMapper;

    @Override
    public int insertImgBatch(Integer id, List<String> pics) {
        int i=0;
        System.out.println(id);
        System.out.println(pics);
        for (String pic : pics) {
             i= productPicMapper.insertImgBatch(id, pic);
        }
        return i;
    }

    @Override
    public int deleteImgBatch(Integer id) {
        QueryWrapper<ProductPic> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(ProductPic::getProductId,id);
        int delete = productPicMapper.delete(queryWrapper);
        return delete;
    }
}

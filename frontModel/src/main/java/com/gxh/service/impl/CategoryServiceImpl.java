package com.gxh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxh.entity.Category;
import com.gxh.mapper.CategoryMapper;
import com.gxh.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl  extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> search(Category category) {
        QueryWrapper<Category> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(Category::getCtStatus,1);
        queryWrapper.lambda().eq(category.getCtRecom()!=null,Category::getCtRecom,1);
        List<Category> categoryList = categoryMapper.selectList(queryWrapper);
        return categoryList;
    }
}

package com.gxh.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxh.dao.CategoryDao;
import com.gxh.entity.Category;
import com.gxh.entity.dto.PageDTO;
import com.gxh.entity.dto.category.CategorySeletPageConditionInDTO;
import com.gxh.entity.dto.category.CategorySeletPageConditionOutDTO;
import com.gxh.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<Category> selectCategoryByParentId(Integer id) {
        LambdaQueryWrapper<Category> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Category::getParentId,id);
        return categoryMapper.selectList(wrapper);
    }

    @Override
    public PageDTO selectIserByPageUseCondition(CategorySeletPageConditionInDTO dto) {
        IPage<Category> iPage=new Page<>(dto.getCurr(),dto.getNums());
        LambdaQueryWrapper<Category> wrapper=new LambdaQueryWrapper<>();

        wrapper.like(dto.getCtName()!=null,Category::getCtName,dto.getCtName());
        wrapper.like(dto.getCtDiscrip()!=null,Category::getCtDiscrip,dto.getCtDiscrip());
        wrapper.eq(dto.getParentId()!=null,Category::getParentId,dto.getParentId());
        wrapper.eq(dto.getCtRecom()!=null,Category::getCtRecom,dto.getCtRecom());
        wrapper.eq(dto.getCtStatus()!=null,Category::getCtStatus,dto.getCtStatus());

        categoryMapper.selectPage(iPage,wrapper);

        PageDTO pageInfo=new PageDTO();
        Long count=iPage.getTotal();
        List<Category> categoryList=iPage.getRecords();

        pageInfo.setCount(count);
        pageInfo.setList(categoryList);

        return pageInfo;
    }

    @Override
    public PageDTO selectByByPageConditionAndParentName(CategorySeletPageConditionInDTO dto) {
        PageDTO pageDTO=new PageDTO();

        PageHelper.startPage(dto.getCurr(),dto.getNums());

        List<CategorySeletPageConditionOutDTO> dtoList= categoryMapper.selectPageConditionAndParentName(dto);

        PageInfo<CategorySeletPageConditionOutDTO> pageInfo=new PageInfo<>(dtoList);
        pageDTO.setCount(pageInfo.getTotal());
        pageDTO.setList(pageInfo.getList());
        return pageDTO;
    }

    @Override
    public List<Map<String, Objects>> getCountData() {
        return categoryMapper.getCountData();
    }
}

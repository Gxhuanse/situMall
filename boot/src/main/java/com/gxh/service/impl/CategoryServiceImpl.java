package com.gxh.service.impl;

import com.gxh.common.ChartBarBean;
import com.gxh.dao.CategoryDao;
import com.gxh.entity.Category;
import com.gxh.entity.dto.PageDTO;
import com.gxh.entity.dto.category.CategorySeletPageConditionInDTO;
import com.gxh.mapper.CategoryMapper;
import com.gxh.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 商品分类 服务实现类
 * </p>
 *
 * @author gxhuanse
 * @since 2023-09-26
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Override
    public List<Category> selectCategoryByParentId(Integer id) {
        return categoryDao.selectCategoryByParentId(id);
    }

    @Override
    public PageDTO selectByByPageConditionAndParentName(CategorySeletPageConditionInDTO dto) {
        return categoryDao.selectByByPageConditionAndParentName(dto);
    }

    @Override
    public ChartBarBean getCountData() {
        ChartBarBean result=new ChartBarBean();
        List<Map<String, Objects>> list=categoryDao.getCountData();
        if (list!=null && !list.isEmpty()){
            for (Map<String, Objects> map : list) {
                result.getCategories().add(map.get("ctName"));
                result.getData().add(map.get("count"));
            }
        }
        return result;
    }
}

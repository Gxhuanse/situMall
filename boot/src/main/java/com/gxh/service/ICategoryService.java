package com.gxh.service;

import com.gxh.common.ChartBarBean;
import com.gxh.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gxh.entity.dto.PageDTO;
import com.gxh.entity.dto.category.CategorySeletPageConditionInDTO;

import java.util.List;

/**
 * <p>
 * 商品分类 服务类
 * </p>
 *
 * @author gxhuanse
 * @since 2023-09-26
 */
public interface ICategoryService extends IService<Category> {

    PageDTO selectByByPageConditionAndParentName(CategorySeletPageConditionInDTO dto);

    List<Category> selectCategoryByParentId(Integer id);

    ChartBarBean getCountData();
}

package com.gxh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gxh.common.ChartBarBean;
import com.gxh.common.ResponseBean;
import com.gxh.entity.Category;
import com.gxh.entity.dto.PageDTO;
import com.gxh.entity.dto.category.CategorySeletPageConditionInDTO;
import com.gxh.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 商品分类 前端控制器
 * </p>
 *
 * @author gxhuanse
 * @since 2023-09-26
 */
@RestController
@RequestMapping("/Category")
public class CategoryController {

    @Autowired
    ICategoryService categoryService;

    @RequestMapping("/selectCategoryByParentId/{id}")
    public ResponseBean selectCategoryByParentId(@PathVariable Integer id){
        ResponseBean responseBean;
        try {
            List<Category> categoryList = categoryService.selectCategoryByParentId(id);
            responseBean= ResponseBean.ok(0,categoryList);
        } catch (Exception e) {
            responseBean=ResponseBean.failed("查询失败");
            throw new RuntimeException(e);
        }
        return responseBean;
    }

    @RequestMapping("/selectByByPageConditionAndParentName")
    public ResponseBean selectByByPageConditionAndParentName(CategorySeletPageConditionInDTO dto){
        ResponseBean responseBean;
        PageDTO pageInfo = null;
        try {
            pageInfo = categoryService.selectByByPageConditionAndParentName(dto);
            responseBean= ResponseBean.ok(0,pageInfo);
        } catch (Exception e) {
            responseBean=ResponseBean.failed("查询失败");
            throw new RuntimeException(e);
        }
        return responseBean;
    }

    @RequestMapping("/selectCategoryByName")
    public ResponseBean selectCategoryByName(Category category){
        ResponseBean responseBean;
        try {
            LambdaQueryWrapper<Category> wrapper=new LambdaQueryWrapper<>();
            wrapper.eq(Category::getCtName,category.getCtName());
            List<Category> list = categoryService.list(wrapper);
            if (list.isEmpty()){
                responseBean= ResponseBean.ok();
            }else responseBean=ResponseBean.failed(500,"此名已重复",list);
        } catch (Exception e) {
            responseBean=ResponseBean.failed("查询出错err");
            throw new RuntimeException(e);
        }
        return responseBean;
    }

    @RequestMapping("/categoryAdd")
    public ResponseBean AddCategory(Category category){
        ResponseBean responseBean=null;
        try {
            boolean save = categoryService.save(category);
            if (save){
                responseBean=ResponseBean.ok("添加成功");
            }
            else responseBean=ResponseBean.failed("添加失败");
        } catch (Exception e) {
            responseBean=ResponseBean.failed("添加失败err");
            throw new RuntimeException(e);
        }
        return responseBean;
    }

    @RequestMapping("/categoryUpdata")
    public ResponseBean UpdateCategory(Category category){
        ResponseBean responseBean=null;
        try {
            boolean save = categoryService.updateById(category);
            if (save){
                responseBean=ResponseBean.ok("修改成功");
            }
            else responseBean=ResponseBean.failed("修改失败");
        } catch (Exception e) {
            responseBean=ResponseBean.failed("修改失败err");
            throw new RuntimeException(e);
        }
        return responseBean;
    }

    @RequestMapping("/categoryDelete")
    public ResponseBean categoryDelete(Category category){
        ResponseBean responseBean=null;
        try {
            List<Category> categoryList = categoryService.selectCategoryByParentId(category.getId());
            if (categoryList.isEmpty()){
                if (true){//todo 判断类别下是否存在商品，判断条件填充
                    boolean save = categoryService.removeById(category);
                    if (save){
                        responseBean=ResponseBean.ok("删除成功");
                    }
                    else responseBean=ResponseBean.failed("删除失败");
                }else responseBean=ResponseBean.failed("修改失败,该类别下存在商品");
            }else responseBean=ResponseBean.failed("修改失败,该类别下存在子类别");
        } catch (Exception e) {
            responseBean=ResponseBean.failed("删除失败err");
            throw new RuntimeException(e);
        }
        return responseBean;
    }

    @RequestMapping("/getCountData")
    public ResponseBean getCountData(){
        ResponseBean responseBean=null;
        try {
            ChartBarBean count = categoryService.getCountData();
            responseBean=ResponseBean.ok(count);
        } catch (Exception e) {
            responseBean=ResponseBean.failed("修改失败err");
            throw new RuntimeException(e);
        }
        return responseBean;
    }

}


package com.gxh.controller.api;

import com.gxh.common.ChartBarBean;
import com.gxh.common.ResponseBean;
import com.gxh.entity.Category;
import com.gxh.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryApiController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseBean search(Category category){
        ResponseBean responseBean=null;
        try {
            List<Category> categoryList= categoryService.search(category);
            responseBean=ResponseBean.ok(categoryList);
        } catch (Exception e) {
            responseBean=ResponseBean.failed("查询失败err");
            throw new RuntimeException(e);
        }
        return responseBean;
    }

    @GetMapping("/{id}")
    public ResponseBean getById(@PathVariable("id") Integer id){
        ResponseBean responseBean=null;
        try {
            Category byId = categoryService.getById(id);
            responseBean=ResponseBean.ok(byId);
        } catch (Exception e) {
            responseBean=ResponseBean.failed("查询失败err");
            throw new RuntimeException(e);
        }
        return responseBean;
    }
}

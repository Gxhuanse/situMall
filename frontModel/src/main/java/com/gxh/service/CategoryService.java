package com.gxh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxh.entity.Category;

import java.util.List;

public interface CategoryService extends IService<Category> {
    List<Category> search(Category category);
}

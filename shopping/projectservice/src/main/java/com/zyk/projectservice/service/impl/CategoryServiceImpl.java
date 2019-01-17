package com.zyk.projectservice.service.impl;

import com.github.pagehelper.Page;
import com.zyk.projectservice.dao.CategoryMapper;
import com.zyk.projectservice.dto.CategoryListDTO;
import com.zyk.projectservice.po.Category;
import com.zyk.projectservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public void add(Category category) {
        categoryMapper.insert(category);
    }

    @Override
    public void batchDelete(Integer[] categoryIds) {
        categoryMapper.deleteByPrimaryKey(categoryIds.length);
    }

    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKey(category);
    }

    @Override
    public Page<CategoryListDTO> getCategoriesWithPage() {
        Page<CategoryListDTO> categories = categoryMapper.getCategories();
        return categories;
    }
}

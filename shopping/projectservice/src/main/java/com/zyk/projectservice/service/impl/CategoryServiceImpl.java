package com.zyk.projectservice.service.impl;

import com.github.pagehelper.Page;
import com.zyk.projectservice.dao.CategoryMapper;
import com.zyk.projectservice.dto.CategoryListDTO;
import com.zyk.projectservice.dto.CategoryUpdateDTO;
import com.zyk.projectservice.po.Category;
import com.zyk.projectservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
        //todo batchDelete
        categoryMapper.batchDelete(categoryIds);
    }

    @Override
    public void update(CategoryUpdateDTO categoryUpdateDTO) {
        categoryMapper.updateByPrimaryKey(categoryUpdateDTO);
    }

    @Override
    public Category getById(String id) {
        Category category = categoryMapper.selectByPrimaryKey(id);
        return category;
    }

    @Override
    public List<Category>  getAllParents() {
        List<Category> allParents = categoryMapper.getAllParents();
        return allParents;
    }

    @Override
    public Page<CategoryListDTO> getCategoriesWithPage() {
        Page<CategoryListDTO> categories = categoryMapper.getCategories();
        return categories;
    }
}

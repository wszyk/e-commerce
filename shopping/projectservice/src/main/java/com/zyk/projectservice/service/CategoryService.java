package com.zyk.projectservice.service;

import com.github.pagehelper.Page;
import com.zyk.projectservice.dto.CategoryListDTO;
import com.zyk.projectservice.dto.CategoryUpdateDTO;
import com.zyk.projectservice.po.Category;

import java.util.List;

public interface CategoryService {



    void add(Category category);
    void batchDelete(Integer[] categoryIds);
    void update(CategoryUpdateDTO categoryUpdateDTO );
    Category getById(String id);
    List<Category> getAllParents();
    Page<CategoryListDTO> getCategoriesWithPage();
}

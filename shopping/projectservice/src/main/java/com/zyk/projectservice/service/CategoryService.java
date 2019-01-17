package com.zyk.projectservice.service;

import com.github.pagehelper.Page;
import com.zyk.projectservice.dto.CategoryListDTO;
import com.zyk.projectservice.po.Category;

public interface CategoryService {

    void add(Category category);
    void batchDelete(Integer[] categoryIds);
    void update(Category category);
    Page<CategoryListDTO> getCategoriesWithPage();
}

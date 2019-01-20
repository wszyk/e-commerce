package com.zyk.projectservice.dao;

import com.github.pagehelper.Page;
import com.zyk.projectservice.dto.CategoryListDTO;
import com.zyk.projectservice.dto.CategoryUpdateDTO;
import com.zyk.projectservice.po.Category;

import java.util.List;

public interface CategoryMapper {
    int batchDelete(Integer[] categoryIds);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category>  getAllParents();

    Category selectByPrimaryKey(String categoryid);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(CategoryUpdateDTO categoryUpdateDTO);

    Page<CategoryListDTO> getCategories();
}
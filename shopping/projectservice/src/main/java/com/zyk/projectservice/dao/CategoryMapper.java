package com.zyk.projectservice.dao;

import com.github.pagehelper.Page;
import com.zyk.projectservice.dto.CategoryListDTO;
import com.zyk.projectservice.po.Category;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer categoryid);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer categoryid);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    Page<CategoryListDTO> getCategories();
}
package com.zyk.projectadminapi.admin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyk.projectservice.dao.CategoryMapper;
import com.zyk.projectservice.dto.CategoryAddDTO;
import com.zyk.projectservice.dto.CategoryListDTO;
import com.zyk.projectservice.dto.CategoryUpdateDTO;
import com.zyk.projectservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @PostMapping("/add")
    public void add(CategoryAddDTO categoryAddDTO){

    }

    @PostMapping("/batchDelete")
    public void batchDelete(@RequestBody Integer[] categoryIds){}


    @PostMapping("/update")
    public void update(@RequestBody CategoryUpdateDTO categoryUpdateDTO){

    }

    @GetMapping("/getCategoriesWithPage")
    public PageInfo<CategoryListDTO> getCategoriesWithPage(@RequestParam(required = false, defaultValue = "1") Integer pageNum){
        Page<CategoryListDTO> categoriesWithPage = categoryService.getCategoriesWithPage();
        PageHelper.startPage(pageNum,3);
        PageInfo<CategoryListDTO> categoryListDTOPageInfo = categoriesWithPage.toPageInfo();
        return categoryListDTOPageInfo;
    }
}

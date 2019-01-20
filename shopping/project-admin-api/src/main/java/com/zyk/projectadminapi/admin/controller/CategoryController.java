package com.zyk.projectadminapi.admin.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyk.projectservice.dao.CategoryMapper;
import com.zyk.projectservice.dto.CategoryAddDTO;
import com.zyk.projectservice.dto.CategoryListDTO;
import com.zyk.projectservice.dto.CategoryUpdateDTO;
import com.zyk.projectservice.po.Category;
import com.zyk.projectservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @PostMapping("/add")
    public void add(Category category){
       categoryService.add(category);
    }

    @GetMapping("/getAllParents")
    public List<Category> getAllParents(){
        List<Category>categories = categoryService.getAllParents();
        return categories;
    }
    @GetMapping("/getById")
    public Category getById(String id){
        Category category = categoryService.getById(id);
        return category;
    }
    @PostMapping("/batchDelete")
    public void batchDelete(@RequestBody Integer[] categoryIds){
        categoryService.batchDelete(categoryIds);
    }


    @PostMapping("/update")
    public void update(@RequestBody CategoryUpdateDTO categoryUpdateDTO){
        categoryService.update(categoryUpdateDTO);
    }

    @GetMapping("/getCategoriesWithPage")
    public PageInfo<CategoryListDTO> getCategoriesWithPage(@RequestParam(required = false, defaultValue = "1") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        Page<CategoryListDTO> categoriesWithPage = categoryService.getCategoriesWithPage();
        PageInfo<CategoryListDTO> categoryListDTOPageInfo = categoriesWithPage.toPageInfo();
        return categoryListDTOPageInfo;
    }
}

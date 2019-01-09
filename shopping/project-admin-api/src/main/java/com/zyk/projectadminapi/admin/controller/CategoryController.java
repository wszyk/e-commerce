package com.zyk.projectadminapi.admin.controller;

import com.zyk.projectservice.dto.CategoryAddDTO;
import com.zyk.projectservice.dto.CategoryListDTO;
import com.zyk.projectservice.dto.CategoryUpdateDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @PostMapping("/add")
    public void add(CategoryAddDTO categoryAddDTO){

    }

    @PostMapping("/batchDelete")
    public void batchDelete(@RequestBody Integer[] categoryIds){}


    @PostMapping("/update")
    public void update(@RequestBody CategoryUpdateDTO categoryUpdateDTO){

    }

    @GetMapping("/getCategoriesWithPage")
    public List<CategoryListDTO> getCategoriesWithPage(@RequestParam(required = false, defaultValue = "1") Integer pageNum){
        return null;
    }
}

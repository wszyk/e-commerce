package com.zyk.projectadminapi.admin.controller;

import com.zyk.projectservice.dto.ProductAddDTO;
import com.zyk.projectservice.dto.ProductListDTO;
import com.zyk.projectservice.dto.ProductUpdateDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @PostMapping("/add")
    public void add(@RequestBody ProductAddDTO productAddDTO){
        //同时操作 商品实体表和商品类目关系表
        //使用事务

    }

    @PostMapping("/batchDelete")
    public void batchDelete(@RequestBody Long[] productIds){

    }

    @PostMapping("/update")
    public void update(@RequestBody ProductUpdateDTO productUpdateDTO){
        //同时操作 商品实体表和商品类目关系表
        //使用事务
    }

    @GetMapping("/searchProducts")
    public List<ProductListDTO> searchProducts(@RequestParam(required = false) String name,
                                               @RequestParam(required = false) Double price,
                                               @RequestParam(required = false, defaultValue = "1") Integer pageNum){
        return null;
    }
}

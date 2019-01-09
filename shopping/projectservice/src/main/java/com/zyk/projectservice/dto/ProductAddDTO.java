package com.zyk.projectservice.dto;

import java.util.List;

public class ProductAddDTO {
    private String productCode;
    private String name;
    private String detail;
    private Double price;
    private Integer vat;
    private String brand;
    private Integer point;
    private String picMainUrl;
    private List<String> picUrls;
    private List<Integer> categoryIds;
}

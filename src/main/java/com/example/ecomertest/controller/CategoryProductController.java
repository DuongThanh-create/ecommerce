package com.example.ecomertest.controller;

import com.example.ecomertest.entity.CategoryProduct;
import com.example.ecomertest.repository.CategoryProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryProductController {
    @Autowired
    private CategoryProductRepo categoryProductRepo;

    @GetMapping("/uploadCategoryProduct")
    public String renderPageCategoryProduct(Model model){
        CategoryProduct cp = new CategoryProduct();
        model.addAttribute("category",cp);
        return "uploadcategoryproduct";
    }
    @PostMapping("/uploadCategoryProduct")
    public String uploadCategoryproduct(@RequestParam(name="name") String name){
        CategoryProduct category = new CategoryProduct();
        category.setName(name);
        categoryProductRepo.save(category);
        return  "redirect:/upload";
    }
}

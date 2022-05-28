package com.example.ecomertest.controller;

import com.example.ecomertest.entity.CategoryProduct;
import com.example.ecomertest.entity.Product;
import com.example.ecomertest.repository.CategoryRepo;
import com.example.ecomertest.repository.ProductRepo;
import com.example.ecomertest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;


@Controller
public class ProductController {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ProductService productSer;
    @Autowired
    private ProductRepo productRepo;
    @GetMapping
    public String renderHomeProduct(Model model, @RequestParam(name="pagenumber", defaultValue = "0") String pagenumber,
                                    @RequestParam(name="number", defaultValue = "6") String number){
        List<CategoryProduct> list = categoryRepo.findAll();
        Pageable pageable = PageRequest.of(Integer.parseInt(pagenumber),Integer.parseInt(number));
        Page<Product> page = productRepo.findAll(pageable);
        List<Product> products = page.stream().toList();
        Integer total = page.getTotalPages();
        model.addAttribute("total",total);
        model.addAttribute("page",pagenumber);
        model.addAttribute("kt",1);
        model.addAttribute("categorys",list);
        model.addAttribute("products",products);
        return "product/list";
    }
    @GetMapping("/product/{id}")
    public String renderProductCategory(Model model, @PathVariable(name="id") Long id){
        List<CategoryProduct> list = categoryRepo.findAll();
        List<Product> products = productRepo.findByCategoryProduct_Id(id);
        model.addAttribute("categorys",list);
        model.addAttribute("products",products);
        model.addAttribute("kt",0);
        return "product/list";
    }
    @GetMapping("/upload")
    public String uploadProduct(Model model){
        Product product = new Product();
        List<CategoryProduct> list = categoryRepo.findAll();
        model.addAttribute(product);
        model.addAttribute("categorys",list);
        return "upload";
    }
    @GetMapping("/product/detail")
    public  String productDetail(Model model, @RequestParam(name="id") Long id){
        List<CategoryProduct> list = categoryRepo.findAll();
        Product p = productRepo.findById(id).get();
        model.addAttribute("categorys",list);
        model.addAttribute("p",p);
        return "product/detail";

    }
    @PostMapping("/upload")
    public String receiveProduct(Product product, @RequestParam(name="fileupload") MultipartFile file, @RequestParam(name="category") Long category){
        productSer.uploadProduct(product,file,category);
        return "redirect:/";
    }

}

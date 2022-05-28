package com.example.ecomertest.service;

import com.example.ecomertest.entity.CategoryProduct;
import com.example.ecomertest.entity.Product;
import com.example.ecomertest.repository.CategoryRepo;
import com.example.ecomertest.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ProductService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ProductRepo productRepo;

    public void uploadProduct(Product product, MultipartFile file, Long category){
        CategoryProduct categoryProduct = categoryRepo.findById(category).get();
        product.setCategoryProduct(categoryProduct);
        product.setImage("/media/"+file.getOriginalFilename());
        productRepo.save(product);
    }
}

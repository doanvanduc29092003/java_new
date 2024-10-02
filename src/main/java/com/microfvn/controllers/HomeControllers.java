package com.microfvn.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.microfvn.models.Category;
import com.microfvn.servicer.CategoryServicer;

@Controller
@RequestMapping("/") // Áp dụng đường dẫn chung cho tất cả phương thức trong controller
public class HomeControllers {

    @Autowired
    private CategoryServicer categoryServicer;

    @GetMapping 
    public String home(Model model) {
        List<Category> categories = categoryServicer.getAll();
        model.addAttribute("categories", categories);
        return "index"; // Trả về template index.html
    }

    @GetMapping("/another") // Tạo một public khác với @GetMapping
    public String anotherPage(Model model) {
        return "index"; 
    }
}

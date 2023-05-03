package com.teamservice.teamservice.controller;

import com.teamservice.teamservice.models.Category;
import com.teamservice.teamservice.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@RestController
@AllArgsConstructor
@RequestMapping("/api/team-service/category")
public class CategoryController {
    private CategoryService categoryService;

    @PostMapping("/save")
    public Category save(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }
    @PutMapping("/update")
    public Category update(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }

    @GetMapping("/getAll")
    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }
    @GetMapping("/get/{id}")
    public Category getCategoryById(@PathVariable("id") String id) {
        return categoryService.findById(id);
    }


}

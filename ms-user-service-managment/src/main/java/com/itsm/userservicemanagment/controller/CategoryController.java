package com.itsm.userservicemanagment.controller;

import com.itsm.userservicemanagment.dto.incoming.category.NewCategory;
import com.itsm.userservicemanagment.dto.incoming.category.NewSubCategory;
import com.itsm.userservicemanagment.dto.outgoing.Result;
import com.itsm.userservicemanagment.dto.outgoing.categorization.Category;
import com.itsm.userservicemanagment.dto.outgoing.categorization.SubCat;
import com.itsm.userservicemanagment.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {


    @Autowired
    private ICategoryService categoryService;


    @PutMapping("/sub")
    public ResponseEntity<Result> createNewSubCategory(@RequestBody NewSubCategory newSubCategory){
        return ResponseEntity.ok(categoryService.createNewSubCategory(newSubCategory));
    }

    @GetMapping("/sub/{catId}")
    public ResponseEntity<SubCat> getSubCategory(@PathVariable Long catId){
        return ResponseEntity.ok(categoryService.findSubCategory(catId));
    }

    @PutMapping("/")
    public ResponseEntity<Result> createNewCategory(@RequestBody NewCategory newCategory){
        return ResponseEntity.ok(categoryService.createNewCategory(newCategory));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.findByCategoryId(id));
    }


    @GetMapping("/ffff")
    public ResponseEntity<?> fdfdf(){
        NewSubCategory category = new NewSubCategory();

        return ResponseEntity.ok(category);
    }

}

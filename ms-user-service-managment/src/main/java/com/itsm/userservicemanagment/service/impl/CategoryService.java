package com.itsm.userservicemanagment.service.impl;


import com.itsm.userservicemanagment.Exception.NotFoundCategoryException;
import com.itsm.userservicemanagment.Exception.NotFoundSubCategoryException;
import com.itsm.userservicemanagment.Exception.NotFoundUserExcption;
import com.itsm.userservicemanagment.dto.incoming.category.NewCategory;
import com.itsm.userservicemanagment.dto.incoming.category.NewSubCategory;
import com.itsm.userservicemanagment.dto.outgoing.Result;
import com.itsm.userservicemanagment.dto.outgoing.categorization.SubCat;
import com.itsm.userservicemanagment.entity.category.*;
import com.itsm.userservicemanagment.repository.CategoryRepository;
import com.itsm.userservicemanagment.repository.SubCategoryRepository;
import com.itsm.userservicemanagment.repository.UserRepository;
import com.itsm.userservicemanagment.service.ICategoryService;
import com.itsm.userservicemanagment.tools.TransferCategoryToFromDtoObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;


    @Autowired
    private UserRepository userRepository;



    @Override
    public Result createNewCategory(NewCategory cat) {
        Result result = new Result();

        Category category = new Category();

        if (subCategoryRepository.findById(cat.getSubCat()).isEmpty())
            throw new NotFoundSubCategoryException("Sub category not found!");

        if (userRepository.findById(cat.getCreateById()).isEmpty())
            throw  new NotFoundUserExcption("User create by not found!");

        if (userRepository.findById(cat.getOwner()).isEmpty())
            throw  new NotFoundUserExcption("User create by not found!");

        category.setCreateDate(LocalDateTime.now());
        category.setActive(true);
        category.setPriorityLevel(cat.getPriorityLevel());
        category.setImpactLevel(cat.getImpactLevel());
        category.setDescription(cat.getDescription());
        category.setCategoryName(cat.getName());
        category.setSubCategory(subCategoryRepository.findById(cat.getSubCat()).get());
        category.setCreateBy(userRepository.findById(cat.getCreateById()).get());
        category.setOwner(userRepository.findById(cat.getOwner()).get());
        category.setCreateByLogin(userRepository.findById(cat.getCreateById()).get().getLogin());

        categoryRepository.save(category);

        result.setDate(LocalDateTime.now());
        result.setMessage("Category by name ["+category.getCategoryName()+"] has been created.");

        return result;
    }

    @Override
    public Result createNewSubCategory(NewSubCategory subCategory) {
        Result result = new Result();
        SubCategory subCat = new SubCategory();

        if (userRepository.findById(subCategory.getCreateById()).isEmpty())
            throw new NotFoundUserExcption("User not found!");

        subCat.setCategoryName(subCategory.getName());
        subCat.setCreateDate(LocalDateTime.now());
        subCat.setDescription(subCategory.getDescription());
        subCat.setCreateByLogin(userRepository.findById(subCategory.getCreateById()).get().getLogin());

        subCategoryRepository.save(subCat);

        result.setMessage("Sub category ["+subCat.getCategoryName()+"] created!");
        result.setDate(LocalDateTime.now());

        return result;
    }

    @Override
    public com.itsm.userservicemanagment.dto.outgoing.categorization.Category findByCategoryId(Long id) {
        if (categoryRepository.findById(id).isEmpty())
            throw new NotFoundCategoryException("Category not found!");

        com.itsm.userservicemanagment.dto.outgoing.categorization.Category category = new com.itsm.userservicemanagment.dto.outgoing.categorization.Category();

        Category catFromDb = categoryRepository.findById(id).get();

        return TransferCategoryToFromDtoObject.getCategoryDTOFromCategoryDB(catFromDb);
    }

    @Override
    public SubCat findSubCategory(Long catId) {

        if (subCategoryRepository.findById(catId).isEmpty())
            throw new NotFoundSubCategoryException("Not found sub category");

        SubCat cat = new SubCat();

        SubCategory subCategory = subCategoryRepository.findById(catId).get();

        cat.setId(subCategory.getId());
        cat.setCreteDate(subCategory.getCreateDate());
        cat.setCategoryName(subCategory.getCategoryName());
        cat.setLastModifyDate(subCategory.getLastModifyDate());
        cat.setDescription(subCategory.getDescription());
        cat.setCreateByLogin(subCategory.getCreateByLogin());

        return cat;
    }
}

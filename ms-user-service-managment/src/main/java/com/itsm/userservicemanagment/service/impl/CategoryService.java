package com.itsm.userservicemanagment.service.impl;


import com.itsm.userservicemanagment.Exception.NotFoundCategoryException;
import com.itsm.userservicemanagment.Exception.NotFoundSubCategoryException;
import com.itsm.userservicemanagment.Exception.NotFoundUserExcption;
import com.itsm.userservicemanagment.dto.incoming.category.NewCategory;
import com.itsm.userservicemanagment.dto.incoming.category.NewSubCategory;
import com.itsm.userservicemanagment.dto.outgoing.Result;
import com.itsm.userservicemanagment.dto.outgoing.categorization.CategoryList;
import com.itsm.userservicemanagment.dto.outgoing.categorization.SubCat;
import com.itsm.userservicemanagment.entity.category.*;
import com.itsm.userservicemanagment.repository.CategoryRepository;
import com.itsm.userservicemanagment.repository.RootCategoryRepository;
import com.itsm.userservicemanagment.repository.UserRepository;
import com.itsm.userservicemanagment.service.ICategoryService;
import com.itsm.userservicemanagment.tools.TransferCategoryToFromDtoObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RootCategoryRepository rootCategoryRepository;


    @Autowired
    private UserRepository userRepository;



    @Override
    public Result createNewCategory(NewCategory cat) {
        Result result = new Result();

        Category category = new Category();

        if (rootCategoryRepository.findById(cat.getSubCat()).isEmpty())
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
        category.setRootCategory(rootCategoryRepository.findById(cat.getSubCat()).get());
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
        RootCategory subCat = new RootCategory();

        if (userRepository.findById(subCategory.getCreateById()).isEmpty())
            throw new NotFoundUserExcption("User not found!");

        subCat.setCategoryName(subCategory.getName());
        subCat.setCreateDate(LocalDateTime.now());
        subCat.setDescription(subCategory.getDescription());
        subCat.setCreateByLogin(userRepository.findById(subCategory.getCreateById()).get().getLogin());

        rootCategoryRepository.save(subCat);

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

        if (rootCategoryRepository.findById(catId).isEmpty())
            throw new NotFoundSubCategoryException("Not found sub category");

        SubCat cat = new SubCat();

        RootCategory rootCategory = rootCategoryRepository.findById(catId).get();

        cat.setId(rootCategory.getId());
        cat.setCreteDate(rootCategory.getCreateDate());
        cat.setCategoryName(rootCategory.getCategoryName());
        cat.setLastModifyDate(rootCategory.getLastModifyDate());
        cat.setDescription(rootCategory.getDescription());
        cat.setCreateByLogin(rootCategory.getCreateByLogin());

        return cat;
    }

    @Override
    public CategoryList getAllCategoriesBySubCat(Long subCatId) {

        CategoryList categoryList = new CategoryList();
        categoryList.setTotal(0);
        HashMap<String, Long> catNames = new HashMap<>();

        if (categoryRepository.findByRootCategoryId(subCatId).isEmpty())
            throw new NotFoundSubCategoryException("Not found sub category by id ");

        List<Category> bySubCategoryId = categoryRepository.findByRootCategoryId(subCatId);

        for (Category cat: bySubCategoryId) {
            catNames.put(cat.getCategoryName(), cat.getId());
        }

        categoryList.setCategory(rootCategoryRepository.findById(subCatId).get().getCategoryName());
        categoryList.setSubCategories(catNames);
        categoryList.setTotal(catNames.size());


        return categoryList;
    }
}

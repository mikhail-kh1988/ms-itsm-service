package com.itsm.userservicemanagment.service;

import com.itsm.userservicemanagment.dto.incoming.category.NewCategory;
import com.itsm.userservicemanagment.dto.incoming.category.NewSubCategory;
import com.itsm.userservicemanagment.dto.outgoing.Result;
import com.itsm.userservicemanagment.dto.outgoing.categorization.Category;
import com.itsm.userservicemanagment.dto.outgoing.categorization.SubCat;

import java.util.List;

public interface ICategoryService {

    Result createNewCategory(NewCategory category);
    Result createNewSubCategory(NewSubCategory subCategory);
    Category findByCategoryId(Long id);
    SubCat findSubCategory(Long catId);
}

package com.itsm.userservicemanagment.tools;


import com.itsm.userservicemanagment.dto.outgoing.categorization.SubCat;
import com.itsm.userservicemanagment.entity.category.Category;
import com.itsm.userservicemanagment.entity.category.Impact;
import com.itsm.userservicemanagment.entity.category.Priority;
import com.itsm.userservicemanagment.entity.category.SubCategory;


public  class TransferCategoryToFromDtoObject {


    public static com.itsm.userservicemanagment.dto.outgoing.categorization.Category getCategoryDTOFromCategoryDB(Category category){


        SubCategory subCategory = category.getSubCategory();

        SubCat subCat = new SubCat();

        com.itsm.userservicemanagment.dto.outgoing.categorization.Category categoryOut = new com.itsm.userservicemanagment.dto.outgoing.categorization.Category();

        categoryOut.setId(category.getId());
        switch (category.getImpactLevel()){
            case ("Overall"):
                categoryOut.setImpact(Impact.Overall);
                break;
            case ("High"):
                categoryOut.setImpact(Impact.High);
                break;
            case ("Medium"):
                categoryOut.setImpact(Impact.Medium);
                break;
            case ("Low") :
                categoryOut.setImpact(Impact.Low);
                break;
        }
        categoryOut.setDescription(category.getDescription());
        categoryOut.setName(category.getCategoryName());
        categoryOut.setActive(category.getActive());
        switch (category.getPriorityLevel()){
            case ("High"):
                categoryOut.setPriority(Priority.High);
                break;
            case ("Medium"):
                categoryOut.setPriority(Priority.Medium);
                break;
            case ("Low"):
                categoryOut.setPriority(Priority.Low);
                break;
        }
        categoryOut.setCreateDate(category.getCreateDate());
        categoryOut.setOwner(category.getOwner().getFullName());
        categoryOut.setOwnerId(category.getOwner().getId());
        categoryOut.setLastModifyDate(category.getLastModifyDate());

        subCat.setCategoryName(subCategory.getCategoryName());
        subCat.setCreteDate(subCategory.getCreateDate());
        subCat.setCreateByLogin(subCategory.getCreateByLogin());
        subCat.setLastModifyDate(subCategory.getLastModifyDate());
        subCat.setDescription(subCategory.getDescription());
        subCat.setId(subCategory.getId());

        categoryOut.setSubCat(subCat);


        return categoryOut;
    }

}

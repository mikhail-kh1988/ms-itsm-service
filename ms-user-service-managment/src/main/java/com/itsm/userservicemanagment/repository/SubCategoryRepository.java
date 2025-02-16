package com.itsm.userservicemanagment.repository;

import com.itsm.userservicemanagment.entity.category.SubCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends CrudRepository<SubCategory, Long> {
}

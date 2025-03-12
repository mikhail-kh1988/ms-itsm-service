package com.itsm.userservicemanagment.repository;

import com.itsm.userservicemanagment.entity.category.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    List<Category> findBySubCategoryId(Long id);

}

package com.itsm.userservicemanagment.repository;

import com.itsm.userservicemanagment.entity.category.RootCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RootCategoryRepository extends CrudRepository<RootCategory, Long> {
}

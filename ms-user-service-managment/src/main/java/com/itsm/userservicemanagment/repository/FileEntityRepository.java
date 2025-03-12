package com.itsm.userservicemanagment.repository;

import com.itsm.userservicemanagment.entity.file.FileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileEntityRepository extends CrudRepository<FileEntity, Long> {
}

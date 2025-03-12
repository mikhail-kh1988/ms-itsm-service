package com.itsm.userservicemanagment.repository;

import com.itsm.userservicemanagment.entity.task.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    //Long findCountById();

    Optional<Task> findByExternalId(String id);

}

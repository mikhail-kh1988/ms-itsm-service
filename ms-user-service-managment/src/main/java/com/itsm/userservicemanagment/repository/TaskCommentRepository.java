package com.itsm.userservicemanagment.repository;

import com.itsm.userservicemanagment.entity.task.TaskComment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskCommentRepository extends CrudRepository<TaskComment, Long> {
}

package com.itsm.userservicemanagment.repository;

import com.itsm.userservicemanagment.entity.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {

    List<Group> findByNotificationTrue();
    List<Group> findByActiveTrue();
    List<Group> findByAssigneeTrue();
    List<Group> findByApprovalTrue();
    List<Group> findBySystemTrue();

}

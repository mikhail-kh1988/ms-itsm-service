package com.itsm.userservicemanagment.repository;

import com.itsm.userservicemanagment.entity.Group;
import com.itsm.userservicemanagment.entity.GroupUser;
import com.itsm.userservicemanagment.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupUserRepository extends CrudRepository<GroupUser, Long> {

    List<Group> findByUserId(Long id);
    List<GroupUser> findByGroupId(Long id);

    @Query(value ="delete from users_groups ug where user_id = ?", nativeQuery = true)
    void removeUser(Long id);

    @Query(value = "select * from users_groups where user_id = ?", nativeQuery = true)
    List<GroupUser> findUserInGroup(Long groupId);
}

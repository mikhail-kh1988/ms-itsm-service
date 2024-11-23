package com.itsm.userservicemanagment.service;

import com.itsm.userservicemanagment.dto.incoming.RoleObject;
import com.itsm.userservicemanagment.dto.outgoing.Result;
import com.itsm.userservicemanagment.dto.outgoing.ShortRole;
import com.itsm.userservicemanagment.entity.Role;

import java.util.List;

public interface IRoleService {

    Result create(RoleObject roleObject);
    Result delete(Long id);
    Result modify(RoleObject roleObject, Long roleId);
    List<ShortRole> getAllRole();
    List<ShortRole> getRoleByUserId(Long userId);
    Role getRoleById(Long id);
}

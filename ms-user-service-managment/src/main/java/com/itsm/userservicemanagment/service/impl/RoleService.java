package com.itsm.userservicemanagment.service.impl;

import com.itsm.userservicemanagment.Exception.NotFoundRoleException;
import com.itsm.userservicemanagment.dto.incoming.RoleObject;
import com.itsm.userservicemanagment.dto.outgoing.Result;
import com.itsm.userservicemanagment.dto.outgoing.ShortRole;
import com.itsm.userservicemanagment.entity.Role;
import com.itsm.userservicemanagment.repository.RoleRepository;
import com.itsm.userservicemanagment.repository.UserRoleRepository;
import com.itsm.userservicemanagment.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository repository;

    private UserRoleRepository repositoryUserRole;

    @Override
    public Result create(RoleObject roleObject) {
        Result result = new Result();
        Role role = new Role();

        role.setDescription(roleObject.getDescription());
        role.setName(roleObject.getRoleName());
        role.setWebPath(roleObject.getPath());

        repository.save(role);

        result.setMessage("Success! Role "+role.getName()+" has been created.");
        result.setDate(LocalDateTime.now());

        return result;
    }

    @Override
    public Result delete(Long id) {
        if (!repository.existsById(id))
            throw new NotFoundRoleException("Role not found!");

        Role role = repository.findById(id).get();

        String roleName = role.getName();

        repository.delete(role);

        Result result = new Result();
        result.setDate(LocalDateTime.now());
        result.setMessage("Role "+roleName+" has been deleted!");

        return result;
    }

    @Override
    public Result modify(RoleObject roleObject, Long roleId) {

        if (!repository.existsById(roleId))
            throw new NotFoundRoleException("Role not found!");

        Result result = new Result();
        Role role = new Role();

        role.setDescription(roleObject.getDescription());
        role.setName(roleObject.getRoleName());
        role.setWebPath(roleObject.getPath());

        repository.save(role);

        result.setMessage("Role "+role.getName()+" has changed!");
        result.setDate(LocalDateTime.now());

        return result;
    }

    @Override
    public List<ShortRole> getAllRole() {
        List<ShortRole> roleList = new ArrayList<>();

        for (Role role: repository.findAll()) {
            ShortRole shortRole = new ShortRole();
            shortRole.setDescription(role.getDescription());
            shortRole.setName(role.getName());
            roleList.add(shortRole);
        }

        return roleList;
    }

    @Override
    public List<ShortRole> getRoleByUserId(Long userId) {
        List<ShortRole> roleList = new ArrayList<>();

        List<Role> rolesByUser = new ArrayList<>();

        for (Role role: repositoryUserRole.findByUser(userId))
            rolesByUser.add(role);

        for (Role role: rolesByUser){
            ShortRole shortRole = new ShortRole();
            shortRole.setName(role.getName());
            shortRole.setDescription(role.getDescription());

            roleList.add(shortRole);
        }

        return roleList;
    }

    @Override
    public Role getRoleById(Long id) {

        if (!repository.existsById(id))
            throw new NotFoundRoleException("Role not found!");


        return repository.findById(id).get();
    }
}

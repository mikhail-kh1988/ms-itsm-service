package com.itsm.userservicemanagment.controller;

import com.itsm.userservicemanagment.dto.incoming.FindUser;
import com.itsm.userservicemanagment.dto.incoming.RoleObject;
import com.itsm.userservicemanagment.dto.incoming.UserObject;
import com.itsm.userservicemanagment.dto.outgoing.Result;
import com.itsm.userservicemanagment.dto.outgoing.ShortRole;
import com.itsm.userservicemanagment.dto.outgoing.UserOutgoing;
import com.itsm.userservicemanagment.entity.Role;
import com.itsm.userservicemanagment.service.IRoleService;
import com.itsm.userservicemanagment.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @PutMapping("/")
    public ResponseEntity<Result> createNewUser(@RequestBody UserObject userObject) throws IllegalAccessException, NoSuchFieldException {
        return ResponseEntity.ok(userService.create(userObject));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserOutgoing> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/tech")
    public ResponseEntity<List<UserOutgoing>> getAllTechUser(){
        return ResponseEntity.ok(userService.getAllTechUser());
    }

    @GetMapping("/active")
    public ResponseEntity<List<UserOutgoing>> getAllActiveUser(){
        return ResponseEntity.ok(userService.getAllActiveNotTechUser());
    }

    @GetMapping("/notactive")
    public ResponseEntity<List<UserOutgoing>> getNotActiveUser(){
        return ResponseEntity.ok(userService.getNotActiveUser());
    }

    @GetMapping("/deleted")
    public ResponseEntity<List<UserOutgoing>> getDeletedUser(){
        return ResponseEntity.ok(userService.getDeleteUser());
    }

    @PostMapping("/find")
    public ResponseEntity<List<UserOutgoing>> findUser(@RequestBody FindUser findUser){
        return ResponseEntity.ok(userService.findUser(findUser.getLogin(), findUser.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.delete(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserOutgoing>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/fff")
    public ResponseEntity<Result> modify(){
        return ResponseEntity.ok(userService.modify(null));
    }

    // Role service realisation.


    @GetMapping("/role/")
    public ResponseEntity<List<ShortRole>> getAllRoles(){
        return ResponseEntity.ok(roleService.getAllRole());
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id){
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    @PutMapping("/role/")
    public ResponseEntity<Result> createNewRole(@RequestBody RoleObject roleObject){
        return ResponseEntity.ok(roleService.create(roleObject));
    }

    @PostMapping("/role/{id}")
    public ResponseEntity<Result> modifyRole(@RequestBody RoleObject roleObject, @PathVariable Long id){
        return ResponseEntity.ok(roleService.modify(roleObject, id));
    }

    @DeleteMapping("/role/{id}")
    public ResponseEntity<Result> deleteRole(@PathVariable Long id){
        return ResponseEntity.ok(roleService.delete(id));
    }

}

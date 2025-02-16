package com.itsm.userservicemanagment.controller;

import com.itsm.userservicemanagment.dto.incoming.ModifyUserGroup;
import com.itsm.userservicemanagment.dto.incoming.GroupObject;
import com.itsm.userservicemanagment.dto.outgoing.GroupOutgoing;
import com.itsm.userservicemanagment.dto.outgoing.Result;
import com.itsm.userservicemanagment.dto.outgoing.UserInGroupOutgoing;
import com.itsm.userservicemanagment.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private IGroupService groupService;

    @PutMapping("/")
    public ResponseEntity<Result> createNewGroup(@RequestBody GroupObject groupObject){
        return ResponseEntity.ok(groupService.createGroup(groupObject));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupOutgoing> getGroupById(@PathVariable Long id){
        return ResponseEntity.ok(groupService.findGroupById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<GroupOutgoing>> getAllGroup(){
        return ResponseEntity.ok(groupService.getAllGroup());
    }


    @GetMapping("/compound/{groupId}")
    public ResponseEntity<UserInGroupOutgoing> getConsistsFromGroup(@PathVariable Long groupId){
        return ResponseEntity.ok(groupService.getCompoundUserFromGroup(groupId));
    }

    @PutMapping("/insert")
    public ResponseEntity<Result> addUserInGroup(@RequestBody ModifyUserGroup add){
        return ResponseEntity.ok(groupService.addUserInGroup(add));
    }

    @DeleteMapping("/user")
    public ResponseEntity<Result> deleteUserFromGroup(@RequestBody ModifyUserGroup deleteUser){
        return ResponseEntity.ok(groupService.deleteUserFromGroup(deleteUser));
    }

    @GetMapping("/notify")
    public ResponseEntity<List<GroupOutgoing>> getGroupNotify(){
        return ResponseEntity.ok(groupService.getAllNotificationGroup());
    }

    @GetMapping("/system")
    public ResponseEntity<List<GroupOutgoing>> getGroupSystem(){
        return ResponseEntity.ok(groupService.getAllSystemGroup());
    }

    @GetMapping("/approval")
    public ResponseEntity<List<GroupOutgoing>> getGroupApproval(){
        return ResponseEntity.ok(groupService.getAllApprovalGroup());
    }

    @GetMapping("/assignee")
    public ResponseEntity<List<GroupOutgoing>> getGroupAssignee(){
        return ResponseEntity.ok(groupService.getAllAssigneeGroup());
    }


}

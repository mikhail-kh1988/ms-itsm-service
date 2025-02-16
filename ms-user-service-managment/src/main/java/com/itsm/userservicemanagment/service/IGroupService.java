package com.itsm.userservicemanagment.service;

import com.itsm.userservicemanagment.dto.incoming.ModifyUserGroup;
import com.itsm.userservicemanagment.dto.incoming.GroupObject;
import com.itsm.userservicemanagment.dto.outgoing.GroupOutgoing;
import com.itsm.userservicemanagment.dto.outgoing.Result;
import com.itsm.userservicemanagment.dto.outgoing.UserInGroupOutgoing;
import com.itsm.userservicemanagment.entity.Group;

import java.util.List;

public interface IGroupService {

    Result createGroup(GroupObject groupObject);
    Result modifyGroup(GroupObject groupObject);
    Result deleteGroup(Long id);
    Result addUserInGroup(ModifyUserGroup modifyUserGroup);
    Result deleteUserFromGroup(ModifyUserGroup modifyUserGroup);
    Group getCleanGroupById(Long id);
    GroupOutgoing findGroupById(Long id);
    UserInGroupOutgoing getCompoundUserFromGroup(Long groupId);
    List<GroupOutgoing> getAllGroup();
    List<GroupOutgoing> findGroupByName(String name);
    List<GroupOutgoing> getAllNotificationGroup();
    List<GroupOutgoing> getAllAssigneeGroup();
    List<GroupOutgoing> getAllApprovalGroup();
    List<GroupOutgoing> getAllSystemGroup();

}

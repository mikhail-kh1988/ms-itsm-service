package com.itsm.userservicemanagment.service;

import com.itsm.userservicemanagment.dto.incoming.GroupObject;
import com.itsm.userservicemanagment.dto.outgoing.GroupOutgoing;
import com.itsm.userservicemanagment.dto.outgoing.Result;
import com.itsm.userservicemanagment.dto.outgoing.UserOutgoing;

import java.util.List;

public interface IGroupService {

    Result createGroup(GroupObject groupObject);
    Result modifyGroup(GroupObject groupObject);
    Result deleteGroup(Long id);
    Result addUserInGroup(Long groupId, Long userId);
    Result deleteUserFromGroup(Long groupId, Long userId);
    GroupOutgoing findGroupById(Long id);
    List<UserOutgoing> getExistUserFromGroup(Long groupId);
    List<GroupOutgoing> getAllGroup();
    List<GroupOutgoing> findGroupByName(String name);
    List<GroupOutgoing> getAllNotificationGroup();
    List<GroupOutgoing> getAllAssigneeGroup();
    List<GroupOutgoing> getAllApprovalGroup();
    List<GroupOutgoing> getAllSystemGroup();

}

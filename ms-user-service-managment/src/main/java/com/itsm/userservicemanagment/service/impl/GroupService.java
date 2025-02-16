package com.itsm.userservicemanagment.service.impl;

import com.itsm.userservicemanagment.Exception.NotFoundGroupException;
import com.itsm.userservicemanagment.Exception.NotFoundUserExcption;
import com.itsm.userservicemanagment.Exception.NotFoundUserInGroupException;
import com.itsm.userservicemanagment.dto.incoming.ModifyUserGroup;
import com.itsm.userservicemanagment.dto.incoming.GroupObject;
import com.itsm.userservicemanagment.dto.outgoing.GroupOutgoing;
import com.itsm.userservicemanagment.dto.outgoing.Result;
import com.itsm.userservicemanagment.dto.outgoing.UserInGroupOutgoing;
import com.itsm.userservicemanagment.entity.Group;
import com.itsm.userservicemanagment.entity.GroupUser;
import com.itsm.userservicemanagment.entity.User;
import com.itsm.userservicemanagment.repository.GroupRepository;
import com.itsm.userservicemanagment.repository.GroupUserRepository;
import com.itsm.userservicemanagment.repository.UserRepository;
import com.itsm.userservicemanagment.service.IGroupService;
import com.itsm.userservicemanagment.tools.GrpOutBuilder;
import com.itsm.userservicemanagment.tools.TransferGroupToFromDtoObject;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log4j
@Service
public class GroupService implements IGroupService {

    private static final Logger LOGGER = LogManager.getLogger();
    @Autowired
    private GroupRepository repository;

    @Autowired
    private GroupUserRepository groupUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Result createGroup(GroupObject groupObject) {

        Result result = new Result();
        Group group = TransferGroupToFromDtoObject.getGroupFromGroupObject(groupObject);

        if(groupObject.getOwnerId() != null) {
            if (!userRepository.existsById(groupObject.getOwnerId()))
                throw new NotFoundUserExcption("User not found!");

            group.setOwner(userRepository.findById(groupObject.getOwnerId()).get());
        }


        repository.save(group);

        result.setDate(LocalDateTime.now());
        result.setMessage("Group ["+group.getName()+"] has been created!");
        return result;
    }

    @Override
    public Result modifyGroup(GroupObject groupObject) {

        return null;
    }

    @Override
    public Result deleteGroup(Long id) {
        return null;
    }

    @Override
    public Result addUserInGroup(ModifyUserGroup modifyUserGroup) {

        GroupUser groupUser = new GroupUser();
        Result result = new Result();

        if (userRepository.findById(modifyUserGroup.getUserId()).isEmpty())
            throw new NotFoundUserExcption("User not found!");

        if(repository.findById(modifyUserGroup.getGroupId()).isEmpty())
            throw new NotFoundGroupException("Group not found!");

        if (userRepository.findById(modifyUserGroup.getCreateByUserId()).isEmpty())
            throw new NotFoundUserExcption("User not found!");

        if (checkUserInGroup(modifyUserGroup.getGroupId(), modifyUserGroup.getUserId())){
            result.setDate(LocalDateTime.now());
            result.setMessage("User by id=["+ modifyUserGroup.getUserId()+"] already exist!");

            return result;
        }


        Group group = repository.findById(modifyUserGroup.getGroupId()).get();
        User user = userRepository.findById(modifyUserGroup.getUserId()).get();
        User createBy = userRepository.findById(modifyUserGroup.getCreateByUserId()).get();

        groupUser.setUser(user);
        groupUser.setGroup(group);
        groupUser.setCreateBy(createBy);
        groupUser.setCreateDate(LocalDateTime.now());
        groupUser.setAction("add");

        groupUserRepository.save(groupUser);

        result.setDate(LocalDateTime.now());
        result.setMessage("User ["+user.getFullName()+"] by login ["+user.getLogin()+"] has been added in group ["+group.getName()+"].");

        return result;
    }

    private Boolean checkUserInGroup(Long group, Long user){
        Group groups = repository.findById(group).get();
        User users = userRepository.findById(user).get();

        List<GroupUser> groupUser = groupUserRepository.findByGroupId(group);

        for (GroupUser gu: groupUser){
            if (gu.getUser().getId().equals(users.getId()))
                return true;
        }

        return false;
    }

    @Override
    public Result deleteUserFromGroup(ModifyUserGroup modifyUserGroup) {

        if (userRepository.findById(modifyUserGroup.getUserId()).isEmpty())
            throw new NotFoundUserExcption("User not found!");

        if(repository.findById(modifyUserGroup.getGroupId()).isEmpty())
            throw new NotFoundGroupException("Group not found!");

        if (userRepository.findById(modifyUserGroup.getCreateByUserId()).isEmpty())
            throw new NotFoundUserExcption("Create by user not found!");

        if (groupUserRepository.findUserInGroup(modifyUserGroup.getGroupId()) == null)
            throw new NotFoundUserInGroupException("Not found user in group!");

        User createBy = userRepository.findById(modifyUserGroup.getCreateByUserId()).get();

        List<GroupUser> groupUserList = groupUserRepository.findByGroupId(modifyUserGroup.getGroupId());


        for (GroupUser gr:groupUserList){
            if (gr.getUser().getId().equals(modifyUserGroup.getUserId()))
                groupUserRepository.removeUser(gr.getUser().getId());

        }

        Result result = new Result();
        result.setDate(LocalDateTime.now());
        result.setMessage("User by id ["+modifyUserGroup.getUserId()+"] has been deleted from group.");
        return result;
    }

    @Override
    public Group getCleanGroupById(Long id) {
        if(repository.findById(id).isEmpty())
            throw new NotFoundGroupException("Group by id="+id+" not found!");

        Group group = repository.findById(id).get();

        return group;
    }

    @Override
    public GroupOutgoing findGroupById(Long id) {

        if(repository.findById(id).isEmpty())
            throw new NotFoundGroupException("Group by id="+id+" not found!");

        GroupOutgoing groupOutgoing = TransferGroupToFromDtoObject.getGroupOutgoing(repository.findById(id).get());

        return groupOutgoing;
    }

    @Override
    public UserInGroupOutgoing getCompoundUserFromGroup(Long groupId) {

        if (repository.findById(groupId).isEmpty())
            throw new NotFoundGroupException("Group by id="+groupId+" not found!");

        Group group = repository.findById(groupId).get();

        List<GroupUser>  userList = groupUserRepository.findByGroupId(groupId);

        UserInGroupOutgoing userInGroupOutgoing = GrpOutBuilder.getUsers(group, userList);

        return userInGroupOutgoing;
    }

    @Override
    public List<GroupOutgoing> getAllGroup() {
        List<GroupOutgoing> groupList = new ArrayList<>();

        for (Group group: repository.findAll())
            groupList.add(TransferGroupToFromDtoObject.getGroupOutgoing(group));

        return groupList;
    }

    @Override
    public List<GroupOutgoing> findGroupByName(String name) {
        return List.of();
    }

    @Override
    public List<GroupOutgoing> getAllNotificationGroup() {
        List<GroupOutgoing> groupList = new ArrayList<>();

        for (Group group: repository.findByNotificationTrue())
            groupList.add(TransferGroupToFromDtoObject.getGroupOutgoing(group));

        return groupList;
    }

    @Override
    public List<GroupOutgoing> getAllAssigneeGroup() {
        List<GroupOutgoing> groupList = new ArrayList<>();

        for (Group group: repository.findByAssigneeTrue())
            groupList.add(TransferGroupToFromDtoObject.getGroupOutgoing(group));

        return groupList;
    }

    @Override
    public List<GroupOutgoing> getAllApprovalGroup() {
        List<GroupOutgoing> groupList = new ArrayList<>();

        for (Group group: repository.findByApprovalTrue())
            groupList.add(TransferGroupToFromDtoObject.getGroupOutgoing(group));

        return groupList;
    }

    @Override
    public List<GroupOutgoing> getAllSystemGroup() {
        List<GroupOutgoing> groupList = new ArrayList<>();

        for (Group group: repository.findBySystemTrue())
            groupList.add(TransferGroupToFromDtoObject.getGroupOutgoing(group));

        return groupList;
    }
}

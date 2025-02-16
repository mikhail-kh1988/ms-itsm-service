package com.itsm.userservicemanagment.tools;


import com.itsm.userservicemanagment.dto.outgoing.GroupType;
import com.itsm.userservicemanagment.dto.outgoing.UserGrp;
import com.itsm.userservicemanagment.dto.outgoing.UserInGroupOutgoing;
import com.itsm.userservicemanagment.entity.Group;
import com.itsm.userservicemanagment.entity.GroupUser;
import com.itsm.userservicemanagment.entity.User;
import com.itsm.userservicemanagment.repository.GroupUserRepository;


import java.util.ArrayList;
import java.util.List;

public class GrpOutBuilder {

    public static UserInGroupOutgoing getUsers(Group group, List<GroupUser> users){
        UserInGroupOutgoing userList = new UserInGroupOutgoing();
        List<UserGrp> list = new ArrayList<>();
        String groupType = "";

        userList.setGroupId(group.getId());
        userList.setGroupName(group.getName());

        if (group.isSystem())
            groupType = GroupType.System.toString();
        else if(group.isApproval())
            groupType = GroupType.Approval.toString();
        else if(group.isAssignee())
            groupType = GroupType.Assignee.toString();
        else if(group.isNotification())
            groupType = GroupType.Notification.toString();

        userList.setGroupType(groupType);


        for (GroupUser groupUser: users){
            if (groupUser.getUser() != null) {
                UserGrp grp = new UserGrp();
                grp.setId(groupUser.getUser().getId());
                grp.setTech(groupUser.getUser().isTechAccount());
                grp.setLogin(groupUser.getUser().getLogin());
                grp.setJobTitle(groupUser.getUser().getJobTitle());
                grp.setFullName(groupUser.getUser().getFullName());
                list.add(grp);
            }
        }

        userList.setTotal(list.size());
        userList.setUsers(list);


        return userList;
    }

}

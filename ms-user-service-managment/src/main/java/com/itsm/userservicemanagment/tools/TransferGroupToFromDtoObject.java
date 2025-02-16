package com.itsm.userservicemanagment.tools;

import com.itsm.userservicemanagment.dto.incoming.GroupObject;
import com.itsm.userservicemanagment.dto.outgoing.GroupOutgoing;
import com.itsm.userservicemanagment.dto.outgoing.GroupType;
import com.itsm.userservicemanagment.entity.Group;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TransferGroupToFromDtoObject {


    public static GroupOutgoing getGroupOutgoing(Group group){

        GroupOutgoing outgoing = new GroupOutgoing();

        outgoing.setId(group.getId());
        if (group.isApproval())
            outgoing.setTypeGroup(GroupType.Approval.toString());
        else if (group.isAssignee())
            outgoing.setTypeGroup(GroupType.Assignee.toString());
        else if(group.isSystem())
            outgoing.setTypeGroup(GroupType.System.toString());
        else if (group.isNotification())
            outgoing.setTypeGroup(GroupType.Notification.toString());

        outgoing.setName(group.getName());
        outgoing.setWorkFrom(group.getWorkPeriodFrom());
        outgoing.setWorkTo(group.getWorkPeriodTo());
        outgoing.setTimeZone(group.getTimeZone());
        if (group.getOwner() == null)
            outgoing.setFullNameOwner("No owner!");
        else
            outgoing.setFullNameOwner(group.getOwner().getFullName());


        return outgoing;
    }

    public static Group getGroupFromGroupObject(GroupObject groupObject){
        Group group = new Group();

        group.setName(groupObject.getName());
        group.setDescription(groupObject.getDescription());
        group.setWorkPeriodTo(groupObject.getWorkTo());
        group.setWorkPeriodFrom(groupObject.getWorkFrom());
        group.setTimeZone(groupObject.getTimeZone());
        group.setCreateDate(LocalDateTime.now());
        group.setApproval(groupObject.isApproval());
        group.setAssignee(groupObject.isAssignee());
        group.setSystem(groupObject.isSystem());
        group.setNotification(groupObject.isNotify());

        return group;
    }
}

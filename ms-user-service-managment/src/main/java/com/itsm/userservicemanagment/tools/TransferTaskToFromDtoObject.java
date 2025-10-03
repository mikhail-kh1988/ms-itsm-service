package com.itsm.userservicemanagment.tools;

import com.itsm.userservicemanagment.dto.outgoing.task.TaskOut;
import com.itsm.userservicemanagment.entity.task.Task;

public class TransferTaskToFromDtoObject {

    public static TaskOut transferFromTask(Task task){

        TaskOut output = new TaskOut();

        output.setId(task.getId());
        output.setBody(task.getBody());
        output.setStatus(task.getStatus().toString());
        output.setTitle(task.getTitle());
        output.setType(task.getType().toString());
        output.setPriority(task.getPriority().toString());
        output.setAssigneeGroupId(task.getAssigneeGroup().getId());
        output.setAssigneeGroupName(task.getAssigneeGroup().getName());
        if (task.getAssigneeUser() == null){
            output.setAssigneeUserId(null);
            output.setAssigneeUserName("No user assignee");
        }else {
            output.setAssigneeUserId(task.getAssigneeUser().getId());
            output.setAssigneeUserName(task.getAssigneeUser().getFullName());
        }
        output.setComments(null);
        output.setLoginCreated(task.getLoginCreated());
        output.setCreateDate(task.getCreateDate());
        output.setLastModifyDate(task.getLastModifyDate());
        output.setDateTo(task.getDateTo());
        output.setDateFrom(task.getDateFrom());


        return output;
    }

}

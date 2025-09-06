package com.itsm.userservicemanagment.service;

import com.itsm.userservicemanagment.dto.incoming.task.NewComment;
import com.itsm.userservicemanagment.dto.incoming.task.NewTask;
import com.itsm.userservicemanagment.dto.incoming.task.UpdateTask;
import com.itsm.userservicemanagment.dto.outgoing.Result;
import com.itsm.userservicemanagment.dto.outgoing.task.TaskAllList;
import com.itsm.userservicemanagment.dto.outgoing.task.TaskListByGroup;
import com.itsm.userservicemanagment.dto.outgoing.task.TaskOut;

public interface ITaskService {

    Result createNewTask(NewTask task);
    Result addCommentToTask(String externalId, NewComment nComment);
    Result modifyTask(UpdateTask update, String taskExternalId);
    Result changeStatusTask(String taskId, Integer status);
    TaskOut getTask(String taskId);
    TaskAllList getAllTask();
    TaskListByGroup findByGroupId(Long id);



}

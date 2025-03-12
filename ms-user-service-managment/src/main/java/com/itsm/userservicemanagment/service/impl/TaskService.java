package com.itsm.userservicemanagment.service.impl;

import com.itsm.userservicemanagment.Exception.NotFoundGroupException;
import com.itsm.userservicemanagment.Exception.NotFoundTaskException;
import com.itsm.userservicemanagment.Exception.NotFoundUserExcption;
import com.itsm.userservicemanagment.dto.incoming.task.NewComment;
import com.itsm.userservicemanagment.dto.incoming.task.NewTask;
import com.itsm.userservicemanagment.dto.incoming.task.UpdateTask;
import com.itsm.userservicemanagment.dto.outgoing.Result;
import com.itsm.userservicemanagment.dto.outgoing.task.TaskAllList;
import com.itsm.userservicemanagment.dto.outgoing.task.TaskListByGroup;
import com.itsm.userservicemanagment.entity.category.Priority;
import com.itsm.userservicemanagment.entity.task.*;
import com.itsm.userservicemanagment.repository.*;
import com.itsm.userservicemanagment.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskService implements ITaskService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskCommentRepository taskCommentRepository;

    @Autowired
    private CommentRepository commentRepository;


    @Override
    public Result createNewTask(NewTask task) {
        Task taskEntity = new Task();

        taskEntity.setTitle(task.getTitle());
        taskEntity.setBody(task.getBody());
        taskEntity.setCreateDate(LocalDateTime.now());
        taskEntity.setStatus(StatusTask.NEW);
        taskEntity.setPriority(Priority.Low);

// ==========================================================================================================
// ===========Logic of work USER ===========================================================================
        if (task.getAssigneeUserId() == null)
            taskEntity.setAssigneeUser(null);
        else if (userRepository.findById(task.getAssigneeUserId()).isEmpty())
            throw new NotFoundUserExcption("User not found!");
        else {
            taskEntity.setAssigneeUser(userRepository.findById(task.getAssigneeUserId()).get());
            taskEntity.setAssigneeUserName(userRepository.findById(task.getAssigneeUserId()).get().getName());
        }
// ==========================================================================================================
// ===========Logic of work GROUP ===========================================================================
        if (task.getAssigneeGroupId() == null) {
            taskEntity.setAssigneeGroup(groupRepository.findById(2L).get());
            taskEntity.setAssigneeGroupName(groupRepository.findById(2L).get().getName());
        } else if (groupRepository.findById(task.getAssigneeGroupId()).isEmpty())
            throw new NotFoundGroupException("Group not found!");
        else {
            taskEntity.setAssigneeGroup(groupRepository.findById(task.getAssigneeGroupId()).get());
            taskEntity.setAssigneeGroupName(groupRepository.findById(task.getAssigneeGroupId()).get().getName());
        }
// ===========================================================================================================


        switch (task.getTypeTask()) {

            case ("1"):
                taskEntity.setType(TypeTask.NONE);
                break;
            case ("2"):
                taskEntity.setType(TypeTask.TECHNO);
                break;
            case ("3"):
                taskEntity.setType(TypeTask.PARENT);
                break;
            default:
                taskEntity.setType(TypeTask.NONE);
                break;
        }

        taskRepository.save(taskEntity);

        String tskId = "TSK" + "-" + taskEntity.getId();
        taskEntity.setExternalId(tskId);

        taskRepository.save(taskEntity);

        Result result = new Result();
        result.setDate(LocalDateTime.now());
        result.setMessage("Task is created: [" + tskId + "]");

        return result;
    }

    @Override
    public Result addCommentToTask(String externalId, NewComment nComment) {
        TaskComment taskComment = new TaskComment();
        Comment commentTask = new Comment();

        if (taskRepository.findByExternalId(externalId).isEmpty())
            throw new NotFoundTaskException("Task not found!");
        else if (userRepository.findById(nComment.getCreateById()).isEmpty())
            throw new NotFoundUserExcption("User not found! ");


        commentTask.setMessage(nComment.getComment());
        commentTask.setCreateBy(userRepository.findById(nComment.getCreateById()).get());
        commentTask.setCreateDate(LocalDateTime.now());

        taskComment.setComment(commentTask);
        taskComment.setTask(taskRepository.findByExternalId(externalId).get());


        commentRepository.save(commentTask);
        taskCommentRepository.save(taskComment);


        Result result = new Result();
        result.setDate(LocalDateTime.now());
        result.setMessage("Add new comment for task [" + externalId + "]");

        return result;
    }

    @Override
    public Result modifyTask(UpdateTask update, String taskExternalId) {

        Task task;
        Result result = new Result();

        if (taskRepository.findByExternalId(taskExternalId).isEmpty())
            throw new NotFoundTaskException("Task not found");
        else
            task = taskRepository.findByExternalId(taskExternalId).get();


        if(update.getAssigneeUserId() != null) {
            if (userRepository.findById(update.getAssigneeUserId()).isEmpty())
                throw new NotFoundUserExcption("User not found.");
            task.setAssigneeUser(userRepository.findById(update.getAssigneeUserId()).get());
        }


        if(update.getAssigneeGroupId() != null){
            if (groupRepository.findById(update.getAssigneeGroupId()).isEmpty())
                throw  new NotFoundGroupException("Group not found.");
            task.setAssigneeGroup(groupRepository.findById(update.getAssigneeGroupId()).get());
        }


        if(update.getLastModifyLoginId() != null) {
            if (userRepository.findById(update.getLastModifyLoginId()).isEmpty())
                throw new NotFoundUserExcption("User not found.");
            task.setLastModifyLogin(userRepository.findById(update.getLastModifyLoginId()).get().getLogin());
        }

        if(update.getAssigneeUserId() != null) {
            if (userRepository.findById(update.getAssigneeUserId()).isEmpty())
                throw new NotFoundUserExcption("User not found.");
            task.setOwner(userRepository.findById(update.getOwnerId()).get());
        }

        if(update.getDateFrom() != null)
            task.setDateFrom(update.getDateFrom());

        if(update.getDateTo() != null)
            task.setDateTo(update.getDateTo());

        if(update.getTitle() != null)
            task.setTitle(update.getTitle());

        if(update.getBody() != null)
            task.setBody(update.getBody());


        switch (update.getStatus()){
            case RUN:
                task.setStatus(StatusTask.RUN);
                break;
            case COMPLETED:
                task.setStatus(StatusTask.COMPLETED);
                break;
            case CLOSE:
                task.setStatus(StatusTask.CLOSE);
                break;
        }

        switch (update.getPriority()){
            case High:
                task.setPriority(Priority.High);
                break;
            case Medium:
                task.setPriority(Priority.Medium);
                break;
            case Low:
                task.setPriority(Priority.Low);
                break;
        }

        task.setLastModifyMate(LocalDateTime.now());


        taskRepository.save(task);

        result.setDate(LocalDateTime.now());
        result.setMessage("Task ["+task.getExternalId()+"] is updated.");


        return result;
    }


    @Override
    public TaskAllList getAllTask() {
        return null;
    }

    @Override
    public TaskListByGroup findByGroupId(Long id) {
        return null;
    }
}
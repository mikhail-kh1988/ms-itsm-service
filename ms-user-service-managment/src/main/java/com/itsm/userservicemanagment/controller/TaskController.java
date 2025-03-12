package com.itsm.userservicemanagment.controller;

import com.itsm.userservicemanagment.dto.incoming.task.NewComment;
import com.itsm.userservicemanagment.dto.incoming.task.NewTask;
import com.itsm.userservicemanagment.dto.incoming.task.UpdateTask;
import com.itsm.userservicemanagment.dto.outgoing.Result;
import com.itsm.userservicemanagment.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private ITaskService taskService;


    @PutMapping("/")
    private ResponseEntity<Result> createNewTask(@RequestBody NewTask nTask){
        return ResponseEntity.ok(taskService.createNewTask(nTask));
    }

    @PutMapping("/comment/{id}")
    private ResponseEntity<Result> addComment(@PathVariable String id, @RequestBody NewComment newComment){
        return ResponseEntity.ok(taskService.addCommentToTask(id, newComment));
    }

    @PostMapping("/{taskId}/update")
    private ResponseEntity<Result> modifyTask(@PathVariable String taskId, @RequestBody UpdateTask updateTask){
        return ResponseEntity.ok(taskService.modifyTask(updateTask, taskId));
    }




}

package com.itsm.userservicemanagment.dto.incoming.task;


import com.itsm.userservicemanagment.entity.category.Priority;
import com.itsm.userservicemanagment.entity.task.StatusTask;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateTask {

    private String title;
    private String body;
    private Long assigneeUserId;
    private Long assigneeGroupId;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private StatusTask status;
    private Priority priority;
    private Long ownerId;
    private Long lastModifyLoginId;

}

package com.itsm.userservicemanagment.dto.incoming.task;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewTask {

    private String title;
    private String body;
    private Long createById;
    private Long assigneeGroupId;
    private Long assigneeUserId;
    private String typeTask;

}

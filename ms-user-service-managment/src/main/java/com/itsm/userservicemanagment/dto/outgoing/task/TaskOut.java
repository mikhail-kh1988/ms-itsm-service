package com.itsm.userservicemanagment.dto.outgoing.task;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class TaskOut {

    private Long id;
    private String title;
    private String body;
    private String priority;
    private String status;
    private String type;
    private String assigneeUserName;
    private Long assigneeUserId;
    private Long assigneeGroupId;
    private String assigneeGroupName;
    private String loginCreated;
    private LocalDateTime createDate;
    private LocalDateTime lastModifyDate;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private String lastModifyLogin;
    private Map<String, String> files;
    private Map<Long, String> comments;

}

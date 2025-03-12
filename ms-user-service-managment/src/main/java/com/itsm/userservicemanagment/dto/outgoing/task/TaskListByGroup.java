package com.itsm.userservicemanagment.dto.outgoing.task;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TaskListByGroup {

    private String groupName;
    private Long closeTask;
    private Long openTask;
    private List<TaskOut> tasks;
}

package com.itsm.userservicemanagment.dto.outgoing.task;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class TaskAllList {

    private long total;
    private Map<Long, TaskOut> outList;

}

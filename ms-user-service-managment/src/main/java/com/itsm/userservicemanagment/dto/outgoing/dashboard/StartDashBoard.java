package com.itsm.userservicemanagment.dto.outgoing.dashboard;

import com.itsm.userservicemanagment.dto.outgoing.UserOutgoing;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;

@Getter
@Setter
public class StartDashBoard {

    private String userName;
    private String jobTitle;
    private HashMap<Long, String> listGroupByPresentUser;
    private Long countAssigneeTask;
    private LocalDateTime lastDateLogin;
    private UserOutgoing user;

}

package com.itsm.userservicemanagment.dto.outgoing;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserInGroupOutgoing {

    private Long groupId;
    private String groupName;
    private String groupType;
    private int total;
    private List<UserGrp> users;

}

package com.itsm.userservicemanagment.dto.incoming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyUserGroup {

    private Long userId;
    private Long groupId;
    private Long createByUserId;

}

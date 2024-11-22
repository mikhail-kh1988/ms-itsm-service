package com.itsm.userservicemanagment.service;

import com.itsm.userservicemanagment.dto.incoming.GroupObject;

public interface IGroupService {

    void createGroup(GroupObject groupObject);
    void modifyGroup(GroupObject groupObject);


}

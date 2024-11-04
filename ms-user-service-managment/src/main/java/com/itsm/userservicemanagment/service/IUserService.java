package com.itsm.userservicemanagment.service;

import com.itsm.userservicemanagment.dto.incoming.UserObject;
import com.itsm.userservicemanagment.dto.outgoing.UserOutgoing;


import java.util.List;

public interface IUserService {

    String create(UserObject user);
    String modify(UserObject user);
    String delete(long id);
    UserOutgoing getUserById(long id);
    List<UserOutgoing> getAllUser();
    List<UserOutgoing> getDeleteUser();
    List<UserOutgoing> getNotActiveUser();

}

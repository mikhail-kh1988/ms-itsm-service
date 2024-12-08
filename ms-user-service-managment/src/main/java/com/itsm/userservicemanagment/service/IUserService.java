package com.itsm.userservicemanagment.service;

import com.itsm.userservicemanagment.dto.incoming.UserObject;
import com.itsm.userservicemanagment.dto.outgoing.Result;
import com.itsm.userservicemanagment.dto.outgoing.UserOutgoing;


import java.util.List;

public interface IUserService {

    Result create(UserObject user) throws IllegalAccessException, NoSuchFieldException;
    Result modify(UserObject user);
    Result changePassword(Long userId, String password);
    Result delete(long id);
    UserOutgoing getUserById(long id);
    List<UserOutgoing> findUser(String login, String name);
    List<UserOutgoing> getAllTechUser();
    List<UserOutgoing> getAllActiveNotTechUser();
    List<UserOutgoing> getAllUser();
    List<UserOutgoing> getDeleteUser();
    List<UserOutgoing> getNotActiveUser();

}

package com.itsm.userservicemanagment.tools;

import com.itsm.userservicemanagment.dto.outgoing.UserOutgoing;
import com.itsm.userservicemanagment.entity.Group;
import com.itsm.userservicemanagment.entity.User;
import com.itsm.userservicemanagment.repository.GroupUserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TransferUserToFromDtoObject {

    @Autowired
    private static GroupUserRepository groupUserRepository;

    public static UserOutgoing getUserOutObject(User user){
        UserOutgoing userOutgoing = new UserOutgoing();

        List<String> groupName = new ArrayList<>();
        List<String> roleName = new ArrayList<>();

        userOutgoing.setId(user.getId());
        userOutgoing.setFullName(user.getFullName());
        userOutgoing.setLogin(user.getLogin());
        userOutgoing.setEmail(user.getEmail());
        userOutgoing.setJobTitle(user.getJobTitle());
        userOutgoing.setActive(user.isActive());
        userOutgoing.setTimeZone(user.getTimeZone());
        userOutgoing.setLanguage(user.getLanguage());
        userOutgoing.setCreateDate(user.getCreateDate());
        userOutgoing.setModifyDate(user.getModifyDate());

        /*if (groupUserRepository.findByUserId(user.getId()) == null) {
            groupName.add("");
        }else {
            for (Group group : groupUserRepository.findByUserId(user.getId()))
                groupName.add(group.getName());
        }*/

        userOutgoing.setGroups(groupName);
        userOutgoing.setRoles(roleName);

        return userOutgoing;
    }
}

package com.itsm.userservicemanagment.aproval;

import com.itsm.userservicemanagment.Exception.GroupIsNotApproval;
import com.itsm.userservicemanagment.entity.Group;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ApprovalService {


    protected String generateUniqueCode(){

        UUID uuid = UUID.randomUUID();

        return uuid.toString();
    }

    private void createApproval(Group group, String textApproval, TypeApproval type, LocalDateTime endDateApprove){

        Approval approval = new Approval();
        approval.setType(type);

        if (group.isApproval()) {
            approval.setGroup(group);
            approval.setTextBodyApproval(textApproval);
            approval.setCreateDate(LocalDateTime.now());
            approval.setEndDate(endDateApprove);
            approval.setStatus(StatusApproval.draft);
            approval.setUniqueApprovalCode(generateUniqueCode());
        }else
            throw new GroupIsNotApproval("Group not for approval");


    }

    protected boolean createBodyApproval(Group group, String text){

        return true;
    }

    private void sendApprovalToApprove(String uuid){

    }

    private void sendVote(String uuid, String vote){

    }

}

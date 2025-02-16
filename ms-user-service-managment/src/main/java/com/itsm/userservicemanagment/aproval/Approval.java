package com.itsm.userservicemanagment.aproval;

import com.itsm.userservicemanagment.entity.Group;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Approval {

    private Long id;
    private Group group;
    private String uniqueApprovalCode;
    private LocalDateTime createDate;
    private LocalDateTime endDate;
    private int countTotalApproval;
    private int contApprovalTrue;
    private int countApprovalFalse;
    private TypeApproval type;
    private StatusApproval status;
    private String textBodyApproval;
    private boolean sendEmail;

}

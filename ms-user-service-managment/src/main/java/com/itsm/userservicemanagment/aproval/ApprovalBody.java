package com.itsm.userservicemanagment.aproval;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApprovalBody {

    private Long id;
    private Approval approval;
    private String login;
    private String email;
    private boolean emailSend;
    private String uniqueApprovalCode;
    private String notifyTextSend;
    private Boolean approve;
    private LocalDateTime sendApprovalEmail;
    private LocalDateTime dateOfApprove;
    private String textApproval;
    private String uniqueTrueApproveCode;
    private String uniqueFalseApproveCode;
}

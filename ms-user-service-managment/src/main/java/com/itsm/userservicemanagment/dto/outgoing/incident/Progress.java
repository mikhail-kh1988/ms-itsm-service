package com.itsm.userservicemanagment.dto.outgoing.incident;

import com.itsm.userservicemanagment.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class Progress {

    private String comment;
    private LocalDateTime createDate;
    private User createBy;
    private String createByLogin;
    private String createByFullName;
    private String doAction;

}

package com.itsm.userservicemanagment.entity.incident;

import com.itsm.userservicemanagment.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class ProgressInWork {

    private Long id;
    private String comment;
    private LocalDateTime createDate;
    private User createBy;
    private String createByLogin;
    private String createByFullName;
    private String doAction;

}

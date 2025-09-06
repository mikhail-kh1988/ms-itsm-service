package com.itsm.userservicemanagment.entity.change;

import com.itsm.userservicemanagment.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChangeActionBody {

    private Long id;
    private ChangeActionBodyStatus status;
    private String action;
    private String actualAction;
    private Boolean pass;
    private LocalDateTime createDate;
    private User createBy;


}

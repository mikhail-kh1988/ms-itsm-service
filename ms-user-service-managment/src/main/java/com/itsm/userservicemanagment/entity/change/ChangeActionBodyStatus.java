package com.itsm.userservicemanagment.entity.change;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeActionBodyStatus {

    private Long id;
    private int code;
    private String logStatusName;
    private boolean action;

}

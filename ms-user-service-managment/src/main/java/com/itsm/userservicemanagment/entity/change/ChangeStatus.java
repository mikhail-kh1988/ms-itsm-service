package com.itsm.userservicemanagment.entity.change;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeStatus {

    private Long id;
    private int code;
    private String status;
    private boolean active;


}

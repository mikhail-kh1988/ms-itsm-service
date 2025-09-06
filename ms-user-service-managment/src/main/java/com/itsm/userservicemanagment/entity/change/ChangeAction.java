package com.itsm.userservicemanagment.entity.change;

import com.itsm.userservicemanagment.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChangeAction {

    private Long id;
    private Change change;
    private ChangeActionBody actionBody;
    private LocalDateTime createDate;
    private User createBy;

}

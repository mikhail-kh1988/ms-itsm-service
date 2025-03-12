package com.itsm.userservicemanagment.entity.ke;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Manufactured {

    private Long id;
    private String name;
    private LocalDateTime createDate;

}

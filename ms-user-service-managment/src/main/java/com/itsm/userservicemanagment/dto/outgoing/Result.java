package com.itsm.userservicemanagment.dto.outgoing;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Result {

    private String message;
    private LocalDateTime date;

}

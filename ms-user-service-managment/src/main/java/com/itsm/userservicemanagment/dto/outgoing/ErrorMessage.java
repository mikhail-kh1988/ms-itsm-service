package com.itsm.userservicemanagment.dto.outgoing;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorMessage {

    private String error;
    private LocalDateTime date;

}

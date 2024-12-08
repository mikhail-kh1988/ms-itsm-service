package com.itsm.userservicemanagment.dto.outgoing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ErrorMessage {

    private String error;
    private LocalDateTime date;

}

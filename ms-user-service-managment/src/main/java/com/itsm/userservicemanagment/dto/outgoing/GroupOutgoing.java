package com.itsm.userservicemanagment.dto.outgoing;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class GroupOutgoing {

    private Long id;
    private String name;
    private String typeGroup;
    private String timeZone;
    private LocalDateTime workTo;
    private LocalDateTime workFrom;
    private String fullNameOwner;

}

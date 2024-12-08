package com.itsm.userservicemanagment.dto.outgoing;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UserOutgoing {
    private Long id;
    private String fullName;
    private String login;
    private String email;
    private String jobTitle;
    private boolean active;
    private String timeZone;
    private String language;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private List<String> groups;
    private List<String> roles;


}

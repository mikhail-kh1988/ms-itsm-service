package com.itsm.userservicemanagment.entity.problem;

import com.itsm.userservicemanagment.entity.Contact;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Problem {

    private Long id;
    private String externalId;
    private Contact whoCreate;
    private String title;
    private String description;



}

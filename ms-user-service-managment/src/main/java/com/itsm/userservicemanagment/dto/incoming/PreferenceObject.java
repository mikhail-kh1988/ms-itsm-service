package com.itsm.userservicemanagment.dto.incoming;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreferenceObject {

    private Long globalId;
    private String key;
    private String value;

}

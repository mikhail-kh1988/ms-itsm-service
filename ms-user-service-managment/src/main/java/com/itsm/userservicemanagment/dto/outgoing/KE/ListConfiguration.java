package com.itsm.userservicemanagment.dto.outgoing.KE;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListConfiguration {

    private int total;
    private List<ConfigurationElement> listKE;
}

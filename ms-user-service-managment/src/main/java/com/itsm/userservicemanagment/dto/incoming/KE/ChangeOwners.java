package com.itsm.userservicemanagment.dto.incoming.KE;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeOwners {

    private Long newOwnerId;
    private Long newOwnerGroupId;

}

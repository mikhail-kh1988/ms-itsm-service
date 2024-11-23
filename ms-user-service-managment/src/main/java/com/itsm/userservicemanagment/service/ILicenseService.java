package com.itsm.userservicemanagment.service;

import com.itsm.userservicemanagment.dto.incoming.LicKey;
import com.itsm.userservicemanagment.dto.outgoing.Result;

public interface ILicenseService {

    Result addLicense(LicKey key);
    void useLicense(String userLogin);
    void deleteLicense(String userLogin);

}

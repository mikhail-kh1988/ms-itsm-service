package com.itsm.userservicemanagment.service.impl;

import com.itsm.userservicemanagment.dto.incoming.LicKey;
import com.itsm.userservicemanagment.dto.outgoing.Result;
import com.itsm.userservicemanagment.repository.LicenseRepository;
import com.itsm.userservicemanagment.service.ILicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LicenseService implements ILicenseService {


    private List<String> fixedUser;
    private List<String> floatUser;

    @Autowired
    private LicenseRepository licenseRepository;

    @Override
    public Result addLicense(LicKey key) {
        return null;
    }

    @Override
    public void useLicense(String userLogin) {

    }

    @Override
    public void deleteLicense(String userLogin) {

    }

    @Override
    public Integer getNumCurrentUser() {

        Integer fixed = fixedUser.size();
        Integer floated = floatUser.size();

        return fixed+floated;
    }

    @Override
    public void parseLicKey(String key) {

    }

    @Override
    public void startLicenseService() {
        fixedUser =  new ArrayList<>();
        floatUser =  new ArrayList<>();


    }
}

package com.itsm.userservicemanagment.service.impl;

import com.itsm.userservicemanagment.repository.TaskRepository;
import com.itsm.userservicemanagment.service.IExternalIDGenerator;
import com.itsm.userservicemanagment.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;

public class ExternalIDGenerator implements IExternalIDGenerator {

    @Autowired
    private TaskRepository taskRepository;

    private  String prefix = "ERR";

    @Override
    public String generateExtId(String entity) {

        switch (entity){
            case ("task"):
                prefix = "TSK";
                break;
            case ("request"):
                prefix = "ERR";
                break;
            default:
                prefix = "EMP";
        }
        //Long countID = taskRepository.findCountById();

        return prefix+"-";
    }

    @Override
    public String getEntityByExtID(String id) {
        return "";
    }
}

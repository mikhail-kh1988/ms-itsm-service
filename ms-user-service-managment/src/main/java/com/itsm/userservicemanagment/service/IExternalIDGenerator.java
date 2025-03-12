package com.itsm.userservicemanagment.service;

public interface IExternalIDGenerator {

    String generateExtId(String entity);
    String getEntityByExtID(String id);
}

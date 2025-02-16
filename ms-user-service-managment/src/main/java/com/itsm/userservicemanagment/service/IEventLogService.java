package com.itsm.userservicemanagment.service;

import com.itsm.userservicemanagment.entity.Event;

import java.util.List;

public interface IEventLogService {

    void addEventLog(String createByLogin, String entity, String operation, String message);
    List<Event> getEventsByEntity(String entity);
    List<Event> getEventsByUser(String login);
    List<Event> getEventsByOperation(String operation);
}

package com.itsm.userservicemanagment.service.impl;

import com.itsm.userservicemanagment.entity.Event;
import com.itsm.userservicemanagment.repository.EventRepository;
import com.itsm.userservicemanagment.service.IEventLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventLogService implements IEventLogService {

    @Autowired
    private EventRepository repository;

    @Override
    public void addEventLog(String createByLogin, String entity, String operation, String message) {

        Event event = new Event();

        event.setEntity(entity);
        event.setMessage(message);
        event.setOperation(operation);
        event.setCreateDate(LocalDateTime.now());
        event.setCreateByLogin(createByLogin);

        repository.save(event);

    }

    @Override
    public List<Event> getEventsByEntity(String entity) {
        return repository.findByEntity(entity);
    }

    @Override
    public List<Event> getEventsByUser(String login) {
        return repository.findByCreateByLogin(login);
    }

    @Override
    public List<Event> getEventsByOperation(String operation) {
        return repository.findByOperation(operation);
    }
}

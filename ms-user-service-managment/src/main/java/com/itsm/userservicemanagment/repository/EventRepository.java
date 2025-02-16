package com.itsm.userservicemanagment.repository;

import com.itsm.userservicemanagment.entity.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

    List<Event> findByEntity(String entity);
    List<Event> findByCreateByLogin(String login);
    List<Event> findByOperation(String operation);
}

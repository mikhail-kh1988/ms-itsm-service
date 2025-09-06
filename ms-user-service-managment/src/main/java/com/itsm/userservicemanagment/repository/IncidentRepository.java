package com.itsm.userservicemanagment.repository;

import com.itsm.userservicemanagment.entity.incident.Incident;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IncidentRepository extends CrudRepository<Incident, Long> {

    List<Incident> findByAssigneeGroup(Long id);
    List<Incident> findByAssignee(Long id);
    List<Incident> findByAssigneeGroupIsNull();
    List<Incident> findByAssigneeIsNull();
    List<Incident> findByCreateDateBetween(LocalDateTime start, LocalDateTime end);
    List<Incident> findByTargetDateBetween(LocalDateTime start, LocalDateTime end);
    Optional<Incident> findByExternalId(String externalId);


}

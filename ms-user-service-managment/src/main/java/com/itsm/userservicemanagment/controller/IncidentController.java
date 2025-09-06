package com.itsm.userservicemanagment.controller;

import com.itsm.userservicemanagment.dto.incoming.incident.NewIncident;
import com.itsm.userservicemanagment.dto.incoming.incident.UpdateIncidentStatuses;
import com.itsm.userservicemanagment.dto.outgoing.Result;
import com.itsm.userservicemanagment.dto.outgoing.incident.IncidentOut;
import com.itsm.userservicemanagment.service.IIncidentManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/incident")
public class IncidentController {

    @Autowired
    private IIncidentManagementService incidentManagement;



    @PutMapping("/")
    private ResponseEntity<Result> createNewIncident(@RequestBody NewIncident incident){
        return ResponseEntity.ok(incidentManagement.createNewIncident(incident));
    }

    @GetMapping("/{incidentId}")
    private ResponseEntity<IncidentOut> getIncidentByExternalId(@PathVariable String incidentId){
        return ResponseEntity.ok(incidentManagement.findByExternalId(incidentId));
    }

    @PostMapping("/{incidentId}")
    private ResponseEntity<Result> modifyIncident( @RequestBody UpdateIncidentStatuses statuses, @PathVariable String incidentId){
        return ResponseEntity.ok(incidentManagement.modifyStatuses(incidentId, statuses));
    }


}

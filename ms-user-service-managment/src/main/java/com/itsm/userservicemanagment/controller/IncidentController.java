package com.itsm.userservicemanagment.controller;

import com.itsm.userservicemanagment.dto.incoming.incident.*;
import com.itsm.userservicemanagment.dto.outgoing.Result;
import com.itsm.userservicemanagment.dto.outgoing.incident.IncidentListOut;
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

    @PostMapping("/resolve/{incidentId}")
    private ResponseEntity<Result> resolveIncident(@RequestBody Resolution resolution, @PathVariable String incidentId){
        return ResponseEntity.ok(incidentManagement.resolve(incidentId, resolution));
    }

    @PostMapping("/modify/categoryKE/{incidentID}")
    private ResponseEntity<Result> changeCategoryOrKE(@RequestBody UpdateIncidentCategorisation categorisation, @PathVariable String incidentID){
        return ResponseEntity.ok(incidentManagement.modifyCategory(incidentID, categorisation));
    }


    @PostMapping("/modify/body/{incidentID}")
    private ResponseEntity<Result> changeBody(@RequestBody UpdateIncidentBodes bodes, @PathVariable String incidentID){
        return ResponseEntity.ok(incidentManagement.modifyBodes(incidentID, bodes));
    }

    @GetMapping("/not_assignee/")
    private ResponseEntity<IncidentListOut> getNotAssigneeIncident(){
        return ResponseEntity.ok(incidentManagement.findByNotAssigneeGroup());
    }

    @PostMapping("/{incidentID}/assignee/{groupID}/group")
    private ResponseEntity<Result> assigneeGroup(@PathVariable String incidentID, @PathVariable Long groupID){
        return ResponseEntity.ok(incidentManagement.modifyAssigneeGroup(groupID, incidentID));
    }

    @GetMapping("/by_group_id/{groupID}")
    private ResponseEntity<IncidentListOut> findByAssigneeGroup(@PathVariable Long groupID){
        return ResponseEntity.ok(incidentManagement.findByAssigneeGroupId(groupID));
    }


}

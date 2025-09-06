package com.itsm.userservicemanagment.controller;

import com.itsm.userservicemanagment.dto.incoming.KE.ChangeOwners;
import com.itsm.userservicemanagment.dto.incoming.KE.ChangeStatus;
import com.itsm.userservicemanagment.dto.incoming.KE.NewConfigurationElement;
import com.itsm.userservicemanagment.dto.incoming.KE.UpdateConfigurationElement;
import com.itsm.userservicemanagment.dto.outgoing.KE.ConfigurationElement;
import com.itsm.userservicemanagment.dto.outgoing.KE.ListConfiguration;
import com.itsm.userservicemanagment.dto.outgoing.Result;
import com.itsm.userservicemanagment.service.IConfigurationElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/KE")
public class ConfigurationElementController {


    @Autowired
    private IConfigurationElementService keService;


    @PutMapping("/")
    public ResponseEntity<Result> createNewKE(@RequestBody NewConfigurationElement ke){
        return ResponseEntity.ok(keService.createNewConfigurationElement(ke));
    }

    @PostMapping("/{keId}/status")
    public ResponseEntity<Result> changeStatus(@RequestBody ChangeStatus status, @PathVariable Long keId ){
        return ResponseEntity.ok(keService.modifyStatusConfigurationElement(status, keId));
    }

    @PostMapping("/owners/{keid}")
    public ResponseEntity<Result> changeOwners(@RequestBody ChangeOwners change, @PathVariable Long keid){
        return ResponseEntity.ok(keService.modifyOwnersConfigurationElement(change, keid));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Result> modifyKE(@RequestBody UpdateConfigurationElement upd, @PathVariable Long id){
        return ResponseEntity.ok(keService.modifyConfigurationElement(upd, id));
    }

    @PutMapping("/subelement/{id}")
    public ResponseEntity<Result> addSubElement(@RequestBody NewConfigurationElement element, @PathVariable Long id){
        return ResponseEntity.ok(keService.addSubElementKE(element, id));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ConfigurationElement> findById(@PathVariable Long id){
        return ResponseEntity.ok(keService.findById(id));
    }

    @GetMapping("/{id}/by-group-owner")
    public ResponseEntity<ListConfiguration> findByGroupOwner(@PathVariable Long id){
        return ResponseEntity.ok(keService.findByOwnerGroupId(id));
    }

    @GetMapping("/{id}/by-owner")
    public ResponseEntity<ListConfiguration> findByOwner(@PathVariable Long id){
        return ResponseEntity.ok(keService.findByOwnerId(id));
    }

    // new
    @GetMapping("/{name}/by-manufactured")
    public ResponseEntity<ListConfiguration> findByManufactured(@PathVariable String name){
        return ResponseEntity.ok(keService.findByManufactured(name));
    }

    @GetMapping("/{prefix}/by-prefix")
    public ResponseEntity<ListConfiguration> findByPrefix(@PathVariable String prefix){
        return ResponseEntity.ok(keService.findByPrefix(prefix));
    }

    @GetMapping("/{status}/by-status")
    public ResponseEntity<ListConfiguration> findByStatus(@PathVariable Long status){
        return ResponseEntity.ok(keService.findByStatus(status));
    }

}

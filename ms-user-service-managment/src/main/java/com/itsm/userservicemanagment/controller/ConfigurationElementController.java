package com.itsm.userservicemanagment.controller;

import com.itsm.userservicemanagment.dto.incoming.KE.ChangeOwners;
import com.itsm.userservicemanagment.dto.incoming.KE.NewConfigurationElement;
import com.itsm.userservicemanagment.dto.incoming.KE.UpdateConfigurationElement;
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

    @PostMapping("/{keId}/{codeStatus}")
    public ResponseEntity<Result> changeStatus(@PathVariable Long keId, @PathVariable Long codeStatus){
        return ResponseEntity.ok(keService.modifyStatusConfigurationElement(codeStatus, keId));
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

}

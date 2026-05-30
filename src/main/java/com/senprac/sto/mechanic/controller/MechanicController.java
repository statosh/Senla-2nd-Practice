package com.senprac.sto.mechanic.controller;

import com.senprac.sto.mechanic.service.MechanicService;
import com.senprac.sto.entity.Mechanic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mechanics")
public class MechanicController {

    private final MechanicService mechanicService;

    public MechanicController(MechanicService mechanicService) {
        this.mechanicService = mechanicService;
    }

    @PostMapping
    public ResponseEntity<Mechanic> addMechanic(@RequestBody Mechanic mechanic) {
        return ResponseEntity.ok(mechanicService.addMechanic(mechanic));
    }

    @GetMapping
    public ResponseEntity<List<Mechanic>> getAllMechanics() {
        return ResponseEntity.ok(mechanicService.getAllMechanics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mechanic> getMechanicById(@PathVariable Long id) {
        return mechanicService.getMechanicById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMechanic(@PathVariable Long id) {
        mechanicService.deleteMechanic(id);
        return ResponseEntity.noContent().build();
    }
}
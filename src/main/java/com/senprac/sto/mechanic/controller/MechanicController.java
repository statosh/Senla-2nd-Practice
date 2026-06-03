package com.senprac.sto.mechanic.controller;

import com.senprac.sto.mechanic.dto.MechanicDto;
import com.senprac.sto.mechanic.service.MechanicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mechanics")
@RequiredArgsConstructor
public class MechanicController {

    private final MechanicService mechanicService;

    @PostMapping
    public ResponseEntity<MechanicDto> addMechanic(@RequestBody MechanicDto mechanicDto) {
        return ResponseEntity.ok(mechanicService.addMechanic(mechanicDto));
    }

    @GetMapping
    public ResponseEntity<List<MechanicDto>> getAllMechanics() {
        return ResponseEntity.ok(mechanicService.getAllMechanics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MechanicDto> getMechanicById(@PathVariable Long id) {
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
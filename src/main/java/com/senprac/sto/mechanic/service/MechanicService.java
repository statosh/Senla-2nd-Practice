package com.senprac.sto.mechanic.service;

import com.senprac.sto.mechanic.repository.MechanicRepository;
import com.senprac.sto.entity.Mechanic;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MechanicService {

    private final MechanicRepository mechanicRepository;

    public MechanicService(MechanicRepository mechanicRepository) {
        this.mechanicRepository = mechanicRepository;
    }

    public Mechanic addMechanic(Mechanic mechanic) {
        return mechanicRepository.save(mechanic);
    }

    public List<Mechanic> getAllMechanics() {
        return mechanicRepository.findAll();
    }

    public Optional<Mechanic> getMechanicById(Long id) {
        return mechanicRepository.findById(id);
    }

    public void deleteMechanic(Long id) {
        mechanicRepository.deleteById(id);
    }
}
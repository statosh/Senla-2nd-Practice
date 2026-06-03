package com.senprac.sto.mechanic.service;

import com.senprac.sto.mechanic.dto.MechanicDto;
import com.senprac.sto.mechanic.mapper.MechanicMapper;
import com.senprac.sto.mechanic.repository.MechanicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MechanicService {

    private final MechanicRepository mechanicRepository;
    private final MechanicMapper mechanicMapper;

    public MechanicDto addMechanic(MechanicDto mechanicDto) {
        return mechanicMapper.toDto(mechanicRepository.save(mechanicMapper.toEntity(mechanicDto)));
    }

    public List<MechanicDto> getAllMechanics() {
        return mechanicRepository.findAll().stream()
                .map(mechanicMapper::toDto)
                .toList();
    }

    public Optional<MechanicDto> getMechanicById(Long id) {
        return mechanicRepository.findById(id).map(mechanicMapper::toDto);
    }

    public void deleteMechanic(Long id) {
        mechanicRepository.deleteById(id);
    }
}
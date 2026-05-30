package com.senprac.sto.mechanic.repository;

import com.senprac.sto.entity.Mechanic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MechanicRepository extends JpaRepository<Mechanic, Long> {
}
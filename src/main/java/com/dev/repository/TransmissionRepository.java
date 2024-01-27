package com.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.model.transmission.Transmission;

public interface TransmissionRepository extends JpaRepository<Transmission, Integer> {
    
}

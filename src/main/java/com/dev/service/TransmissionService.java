package com.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dev.model.transmission.Transmission;

@Service
public interface TransmissionService {
    public List<Transmission> findAllTransmission();

    public Transmission save(Transmission transmission);

    public Transmission update(int idTransmission, String nomTransmission);
    
    public void delete(int idTransmission);

    public Optional<Transmission> findById(int idTransmission);
}

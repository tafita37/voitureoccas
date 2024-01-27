package com.dev.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dev.model.transmission.Transmission;
import com.dev.repository.TransmissionRepository;
import com.dev.service.TransmissionService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransmissionServiceImpl implements TransmissionService {
    private final TransmissionRepository transmissionRepository;

    @Override
    public List<Transmission> findAllTransmission() {
        return transmissionRepository.findAll();
    }

    @Override
    public Transmission save(Transmission transmission) {
        return transmissionRepository.save(transmission);
    }

    @Override
    public Transmission update(int idTransmission, String nomTransmission) {
        Transmission transmission=transmissionRepository.findById(idTransmission).get();
        transmission.setNomTransmission(nomTransmission);
        return transmissionRepository.save(transmission);
    }

    @Override
    public void delete(int idTransmission) {
        transmissionRepository.deleteById(idTransmission);
    }

    @Override
    public Optional<Transmission> findById(int idTransmission) {
        return transmissionRepository.findById(idTransmission);
    }
    
}

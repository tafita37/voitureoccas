package com.dev.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.model.transmission.Transmission;
import com.dev.service.TransmissionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transmission")
public class TransmissionController {
    private final TransmissionService transmissionService;

    @GetMapping(path = "/allTransmission", produces = "application/json")
    public List<Transmission> getAllTransmission() {
        return transmissionService.findAllTransmission();
    }

    @PostMapping("/insertTransmission")
    public Transmission insertTransmission(@RequestBody Transmission transmission) {
        return transmissionService.save(transmission);
    }

    @PostMapping("/updateTransmission")
    public Transmission updateTransmission(@RequestParam int idTransmission, @RequestParam String nomTransmission) {
        System.out.println(idTransmission+" "+nomTransmission);
        return transmissionService.update(idTransmission, nomTransmission);
    }  

    @GetMapping("/findTransmissionById")
    public Transmission findTransmissionById(@RequestParam int idTransmission) {
        try {
            return transmissionService.findById(idTransmission).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/deleteTransmission")
    public void deleteTransmission(@RequestParam int idTransmission) {
        transmissionService.delete(idTransmission);
    }


}

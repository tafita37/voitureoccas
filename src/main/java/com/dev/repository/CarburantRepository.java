package com.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.model.carburant.Carburant;

public interface CarburantRepository extends JpaRepository<Carburant, Integer>  {
    
}

package com.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.model.marque.Marque;

@Repository
public interface MarqueRepository extends JpaRepository<Marque, Integer> {
    
}

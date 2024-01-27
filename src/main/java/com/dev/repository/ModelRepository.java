package com.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.model.models.Model;

public interface ModelRepository extends JpaRepository<Model, Integer> {
    
}

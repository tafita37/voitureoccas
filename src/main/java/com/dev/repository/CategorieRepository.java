package com.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dev.model.categorie.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
    
}

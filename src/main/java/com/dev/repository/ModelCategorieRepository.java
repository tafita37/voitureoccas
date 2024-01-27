package com.dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.model.models.Model;
import com.dev.model.models.ModelCategorie;
import java.util.List;
import com.dev.model.categorie.Categorie;


public interface ModelCategorieRepository extends JpaRepository<ModelCategorie, Integer> {
    public List<ModelCategorie> findByCategorieAndModel(Categorie categorie, Model model);

    public void deleteByModel(Model model);
}

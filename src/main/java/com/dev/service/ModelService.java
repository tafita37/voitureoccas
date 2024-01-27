package com.dev.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.dev.model.models.Model;
import com.dev.model.models.model.ModelGet;

@Service
public interface ModelService {
    public List<Model> findAllModel();

    public Model save(ModelGet modelGet);

    public Model update(int idModel, String nomModel, double vitesse, int idTransmission, Date dateSortie, int idMarque);
    
    public void delete(int idModel);

    public Optional<Model> findModelById(int idModel);
}

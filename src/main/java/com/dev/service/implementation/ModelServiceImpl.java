package com.dev.service.implementation;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dev.model.categorie.Categorie;
import com.dev.model.models.Model;
import com.dev.model.models.ModelCategorie;
import com.dev.model.models.model.ModelGet;
import com.dev.repository.CarburantRepository;
import com.dev.repository.MarqueRepository;
import com.dev.repository.ModelCategorieRepository;
import com.dev.repository.ModelRepository;
import com.dev.repository.TransmissionRepository;
import com.dev.service.ModelService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final ModelCategorieRepository modelCategorieRepository;
    private final TransmissionRepository transmissionRepository;
    private final MarqueRepository marqueRepository;
    private final CarburantRepository carburantRepository;

    @Override
    public List<Model> findAllModel() {
        return modelRepository.findAll();
    }

    @Override
    public Model save(ModelGet modelGet) {
        Model result=null;
        boolean misy=false;
        if(modelGet.getCheck_categorie()==null) {
            misy=false;
        }
        for(int i=0; i<modelGet.getCheck_categorie().size(); i++) {
            if(modelGet.getCheck_categorie().get(i).isChecked()) {
                misy=true;
            }
        }
        if(misy) {
            Model model=new Model();
            model.setIdModel(modelGet.getIdModel());
            model.setNomModel(modelGet.getNomModel());
            model.setVitesse(modelGet.getVitesse());
            model.setTransmission(transmissionRepository.findById(modelGet.getTransmission()).get());
            model.setDateSortie(modelGet.getDateSortie());
            model.setMarque(marqueRepository.findById(modelGet.getMarque()).get());
            model.setCarburant(carburantRepository.findById(modelGet.getCarburant()).get());
            result =  modelRepository.save(model);
            result.setListeModelCategorie(new ArrayList<ModelCategorie>());
            for(int i=0; i<modelGet.getCheck_categorie().size(); i++) {
                System.out.println(modelGet.getCheck_categorie().get(i).isChecked());
                if(modelGet.getCheck_categorie().get(i).isChecked()) {
                    ModelCategorie modelCategorie=new ModelCategorie();
                    modelCategorie.setCategorie(new Categorie(modelGet.getCheck_categorie().get(i).getIdCategorie(), modelGet.getCheck_categorie().get(i).getNomCategorie()));
                    modelCategorie.setModel(model);
                    modelCategorieRepository.save(modelCategorie);
                    result.getListeModelCategorie().add(modelCategorie);
                }
            }
        }
        return result;
    }

    @Override
    public Model update(int idModel, String nomModel, double vitesse, int idTransmission, Date dateSortie, int idMarque) {
        Model model=modelRepository.findById(idModel).get();
        model.setNomModel(nomModel);
        model.setVitesse(vitesse);
        model.setTransmission(transmissionRepository.findById(idTransmission).get());
        model.setDateSortie(dateSortie);
        model.setMarque(marqueRepository.findById(idMarque).get());
        return modelRepository.save(model);
    }

    @Override
    public void delete(int idModel) {
        Model model=modelRepository.findById(idModel).get();
        modelCategorieRepository.deleteByModel(model);
        modelRepository.deleteById(idModel);
    }

    @Override
    public Optional<Model> findModelById(int idModel) {
        return modelRepository.findById(idModel);
    }
}

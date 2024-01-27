package com.dev.model.models.model;

import java.sql.Date;
import java.util.List;
import com.dev.model.carburant.Carburant;
import com.dev.model.categorie.model.CategorieChecked;
import com.dev.model.marque.Marque;
import com.dev.model.transmission.Transmission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelGet {
    int idModel;
    String nomModel;
    double vitesse;
    int transmission;
    Date dateSortie;
    int marque;
    int carburant;
    List<CategorieChecked> check_categorie;
}

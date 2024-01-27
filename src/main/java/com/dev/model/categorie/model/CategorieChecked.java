package com.dev.model.categorie.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CategorieChecked {
    Integer idCategorie;
    String nomCategorie;
    @JsonProperty("isChecked")
    boolean isChecked;
    public Integer getIdCategorie() {
        return idCategorie;
    }
    public void setIdCategorie(Integer idCategorie) {
        this.idCategorie = idCategorie;
    }
    public String getNomCategorie() {
        return nomCategorie;
    }
    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }
    public boolean isChecked() {
        return isChecked;
    }
    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}

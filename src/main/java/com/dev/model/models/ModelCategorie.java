package com.dev.model.models;

import com.dev.model.categorie.Categorie;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "modelcategorie")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelCategorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmodelcategorie", nullable = false, columnDefinition = "INTEGER")
    int idModelCategorie;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idmodel", nullable = false, columnDefinition = "INTEGER")
    Model model;
    @ManyToOne
    @JoinColumn(name = "idcategorie", nullable = false, columnDefinition = "INTEGER")
    Categorie categorie;
}

package com.dev.model.models;

import java.sql.Date;
import java.util.List;

import com.dev.model.carburant.Carburant;
import com.dev.model.marque.Marque;
import com.dev.model.transmission.Transmission;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "models")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmodel", nullable = false, columnDefinition = "INTEGER")
    int idModel;
    @Column(name = "nommodel", nullable = false, columnDefinition = "VARCHAR(50)")
    String nomModel;
    @Column(name = "vitesse", nullable = false, columnDefinition = "DOUBLE PRECISION")
    double vitesse;
    @ManyToOne
    @JoinColumn(name = "idtransmission", nullable = false, columnDefinition = "INTEGER")
    Transmission transmission;
    @Column(name = "datesortie", nullable = false, columnDefinition = "DATE")
    Date dateSortie;
    @ManyToOne
    @JoinColumn(name = "idmarque", nullable = false, columnDefinition = "INTEGER")
    Marque marque;
    @ManyToOne
    @JoinColumn(name = "idcarburant", nullable = false, columnDefinition = "INTEGER")
    Carburant carburant;
    @OneToMany(mappedBy = "model")
    List<ModelCategorie> listeModelCategorie;
}

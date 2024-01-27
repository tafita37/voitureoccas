package com.dev.model.categorie;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categorie")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcategorie", nullable = false, columnDefinition = "INTEGER")
    Integer idCategorie;

    @Column(name = "nomcategorie", columnDefinition = "VARCHAR(50)", unique = true)
    String nomCategorie;    
}

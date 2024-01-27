package com.dev.model.carburant;

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
@Table(name = "carburant")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Carburant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcarburant", nullable = false, columnDefinition = "INTEGER")
    Integer idCarburant;

    @Column(name = "nomcarburant", columnDefinition = "VARCHAR(50)", unique = true)
    String nomcarburant;  
}

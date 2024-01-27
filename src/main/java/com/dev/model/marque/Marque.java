package com.dev.model.marque;

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
@Table(name = "marque")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Marque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmarque", nullable = false, columnDefinition = "INTEGER")
    Integer idMarque;

    @Column(name = "nommarque", columnDefinition = "VARCHAR(50)", unique = true)
    String nomMarque;
}

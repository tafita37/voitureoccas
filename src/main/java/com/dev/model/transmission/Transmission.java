package com.dev.model.transmission;

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
@Table(name = "transmission")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtransmission", nullable = false, columnDefinition = "INTEGER")
    int idTransmission;
    @Column(name = "nomtransmission", nullable = false, columnDefinition = "VARCHAR(50)")
    String nomTransmission;
}

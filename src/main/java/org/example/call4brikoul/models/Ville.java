package org.example.call4brikoul.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
public class Ville {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ville;

    @Column(name = "nom")
    private String nom;

    @ManyToOne
    @JoinColumn(name = "region_id")
    @JsonIgnore
    private Region region;
}


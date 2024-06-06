package org.example.call4brikoul.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reg;
    private String nom;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Address> addresses;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ville> villes;

}
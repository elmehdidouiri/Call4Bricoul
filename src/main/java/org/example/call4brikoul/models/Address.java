package org.example.call4brikoul.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id_adr;
    private String rue;

    @ManyToOne
    @JoinColumn(name = "region_id", referencedColumnName = "id_reg")
    @JsonIgnore
    private Region region;

    @ManyToOne
    @JoinColumn(name = "ville_id", referencedColumnName = "id_ville")
    private Ville ville;

    @OneToOne(mappedBy = "adresse")
    @JsonIgnore
    private User user;

}


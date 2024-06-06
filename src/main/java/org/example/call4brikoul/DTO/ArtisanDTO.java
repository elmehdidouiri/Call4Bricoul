package org.example.call4brikoul.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.call4brikoul.models.Address;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtisanDTO {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String telephone;
    private Integer anneesExperience;
    private List<Long> specialitesId;
    private AddressDTO adresse;

}

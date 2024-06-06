package org.example.call4brikoul.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ClientDTO {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String telephone;

    private AddressDTO adresse;
}

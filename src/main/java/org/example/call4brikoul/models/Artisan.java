package org.example.call4brikoul.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

    public class Artisan extends User {

        @Column(name = "date_enregistrement")
        private LocalDate dateEnregistrement;

        @Column(name = "annees_experience")
        private Integer anneesExperience;
        @ManyToMany
        @JoinTable(
                name = "artisan_specialite",
                joinColumns = @JoinColumn(name = "artisan_id"),
                inverseJoinColumns = @JoinColumn(name = "specialite_id")
        )
        private List<Specialite> specialites;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_profil_id")
    @JsonIgnoreProperties("artisan")  
    private PhotoProfil photoProfil;


    @OneToMany(mappedBy = "artisan")
    @JsonIgnore
    private List<Commentaire> commentaires;


    }

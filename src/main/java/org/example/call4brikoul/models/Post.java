package org.example.call4brikoul.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    @Column(length = 2000)
    private String description;

    private LocalDate dateCreation = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "artisan_id")
    @JsonIgnore  // Évite la boucle infinie dans le JSON
    private Artisan artisan;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PhotoPost> photos;
}

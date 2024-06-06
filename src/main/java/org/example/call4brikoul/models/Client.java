package org.example.call4brikoul.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
public class Client extends User {
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Commentaire> commentaires;

}
package org.example.call4brikoul.Repository;

import org.example.call4brikoul.models.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Long> {
}

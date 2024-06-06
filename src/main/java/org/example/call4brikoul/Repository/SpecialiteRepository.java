package org.example.call4brikoul.Repository;

import org.example.call4brikoul.models.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialiteRepository extends JpaRepository<Specialite, Long> {
}



package org.example.call4brikoul.Repository;

 import org.example.call4brikoul.models.PhotoProfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoProfilRepository extends JpaRepository<PhotoProfil, Long> {
    PhotoProfil findByArtisanId(Long artisanId);

}


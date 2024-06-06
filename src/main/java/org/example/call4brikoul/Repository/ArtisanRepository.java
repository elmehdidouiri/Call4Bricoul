package org.example.call4brikoul.Repository;

import org.example.call4brikoul.models.Artisan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtisanRepository extends JpaRepository<Artisan, Long> {
    @Query("SELECT a FROM Artisan a WHERE a.adresse.ville.nom = :villeNom")
    List<Artisan> findByVilleNom(@Param("villeNom") String villeNom);
    @Query("""
        SELECT DISTINCT a
        FROM Artisan a
        JOIN a.adresse ad
        JOIN ad.ville v
        WHERE v.nom = :villeNom
    """)
    List<Artisan> findArtisansByVille(@Param("villeNom") String villeNom);
    List<Artisan> findByAdresse_Ville_Nom(String villeNom);



    @Query("SELECT a FROM Artisan a JOIN a.specialites s WHERE s.nom = :specialiteNom")
    List<Artisan> findBySpecialitesNom(@Param("specialiteNom") String specialiteNom);
}


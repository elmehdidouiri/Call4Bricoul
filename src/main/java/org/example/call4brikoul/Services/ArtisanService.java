package org.example.call4brikoul.Services;

import jakarta.transaction.Transactional;
import org.example.call4brikoul.DTO.ArtisanDTO;

import org.example.call4brikoul.Repository.*;
import org.example.call4brikoul.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ArtisanService {

    private final ArtisanRepository artisanRepository;
    private final SpecialiteRepository specialiteRepository;
    private final VilleRepository villeRepository;
    private final AddressRepository addressRepository;
    private final RegionRepository regionRepository;
    private final PhotoProfilRepository photoRepository;
    private final String uploadDir = "uploads/profils/";



    @Autowired
    public ArtisanService(ArtisanRepository artisanRepository,
                          AddressRepository addressRepository,
                          SpecialiteRepository specialiteRepository,
                          VilleRepository villeRepository,
                          RegionRepository regionRepository, PhotoProfilRepository photoRepository) {
        this.artisanRepository = artisanRepository;
        this.specialiteRepository = specialiteRepository;
        this.villeRepository = villeRepository;
        this.addressRepository = addressRepository;
        this.regionRepository = regionRepository;
        this.photoRepository = photoRepository;

    }
    @Transactional
    public Artisan createArtisan(ArtisanDTO artisanDTO, MultipartFile photo) throws IOException {
        Artisan artisan = new Artisan();
        artisan.setNom(artisanDTO.getNom());
        artisan.setPrenom(artisanDTO.getPrenom());
        artisan.setEmail(artisanDTO.getEmail());
        artisan.setPassword(artisanDTO.getPassword());
        artisan.setTelephone(artisanDTO.getTelephone());
        artisan.setAnneesExperience(artisanDTO.getAnneesExperience());
        artisan.setDateEnregistrement(LocalDate.now());
        // Spécialités
        if (artisanDTO.getSpecialitesId() != null) {
            List<Specialite> specialites = specialiteRepository.findAllById(artisanDTO.getSpecialitesId());
            artisan.setSpecialites(specialites);
        }

        // Adresse
        if (artisanDTO.getAdresse() != null) {
            Address adresse = new Address();
            adresse.setRue(artisanDTO.getAdresse().getRue());
            Ville ville = villeRepository.findById(artisanDTO.getAdresse().getVilleId())
                    .orElseThrow(() -> new RuntimeException("Ville non trouvée"));
            Region region = regionRepository.findById(artisanDTO.getAdresse().getRegionId())
                    .orElseThrow(() -> new RuntimeException("Région non trouvée"));
            adresse.setVille(ville);
            adresse.setRegion(region);
            addressRepository.save(adresse);
            artisan.setAdresse(adresse);
        }

        // Sauvegarde artisan avant photo
        Artisan savedArtisan = artisanRepository.save(artisan);

        if (photo != null && !photo.isEmpty()) {

            String fileName = UUID.randomUUID() + "_" + photo.getOriginalFilename();
            Path uploadDir = Paths.get("uploads/profiles");

            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            Files.copy(photo.getInputStream(), uploadDir.resolve(fileName));

            PhotoProfil photoProfil = new PhotoProfil();
            photoProfil.setUrl("/uploads/profiles/" + fileName);

            // 🔗 RELATION BIDIRECTIONNELLE
            photoProfil.setArtisan(artisan);
            artisan.setPhotoProfil(photoProfil);
        }

        // 🔥 UN SEUL SAVE
        return artisanRepository.save(artisan);
    }

    public List<Artisan> getAllArtisans() {
        return artisanRepository.findAll();
    }

    public Artisan getArtisanById(Long id) {
        return artisanRepository.findById(id).orElse(null);
    }



    public Artisan updateArtisan(Long id, Artisan artisanDetails) {
        Artisan artisan = artisanRepository.findById(id).orElse(null);
        if (artisan == null) return null;

        artisan.setNom(artisanDetails.getNom());
        artisan.setPrenom(artisanDetails.getPrenom());
        artisan.setEmail(artisanDetails.getEmail());
        artisan.setTelephone(artisanDetails.getTelephone());
        artisan.setAnneesExperience(artisanDetails.getAnneesExperience());
        artisan.setDateEnregistrement(artisanDetails.getDateEnregistrement());
        artisan.setSpecialites(artisanDetails.getSpecialites());
        artisan.setPhotoProfil(artisanDetails.getPhotoProfil());
        artisan.setCommentaires(artisanDetails.getCommentaires());
        artisan.setAdresse(artisanDetails.getAdresse());

        return artisanRepository.save(artisan);
    }

    public void deleteArtisan(Long id) {
        artisanRepository.deleteById(id);
    }

    @Transactional
    public List<Artisan> findArtisansByVille(String villeNom) {
        // Récupérer les artisans dont l'adresse correspond à la ville
        List<Artisan> artisans = artisanRepository.findByAdresse_Ville_Nom(villeNom);

        // Déconnecter les relations qui peuvent créer des boucles infinies
        for (Artisan artisan : artisans) {
            if (artisan.getAdresse() != null) {
                artisan.getAdresse().setRegion(null); // éviter JSON infini
                artisan.getAdresse().setUser(null);   // éviter JSON infini
            }
            if (artisan.getPhotoProfil() != null) {
                // Si besoin, laisser tel quel pour afficher l'URL
            }
            if (artisan.getCommentaires() != null) {
                artisan.setCommentaires(null); // éviter JSON infini
            }
        }

        return artisans;
    }


    public List<Artisan> findArtisansBySpecialite(String specialiteNom) {
        return artisanRepository.findBySpecialitesNom(specialiteNom);
    }
}

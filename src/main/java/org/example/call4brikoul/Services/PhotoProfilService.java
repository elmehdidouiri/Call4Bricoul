package org.example.call4brikoul.Services;

import org.example.call4brikoul.Repository.ArtisanRepository;
import org.example.call4brikoul.Repository.PhotoProfilRepository;
import org.example.call4brikoul.models.Artisan;
import org.example.call4brikoul.models.PhotoProfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class PhotoProfilService {

    private final PhotoProfilRepository photoProfilRepository;
    private final ArtisanRepository artisanRepository;

    private final String UPLOAD_DIR = "uploads/profils/";

    @Autowired
    public PhotoProfilService(PhotoProfilRepository photoProfilRepository,
                              ArtisanRepository artisanRepository) {
        this.photoProfilRepository = photoProfilRepository;
        this.artisanRepository = artisanRepository;
    }

    public PhotoProfil uploadPhotoProfil(Long artisanId, MultipartFile file) throws IOException {

        Artisan artisan = artisanRepository.findById(artisanId)
                .orElseThrow(() -> new RuntimeException("Artisan non trouvé"));

        // Créer le dossier s'il n'existe pas
        Files.createDirectories(Paths.get(UPLOAD_DIR));

        // Nom unique du fichier
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + filename);

        // Sauvegarde physique
        Files.write(filePath, file.getBytes());

        // Supprimer ancienne photo si existe
        PhotoProfil existing = photoProfilRepository.findByArtisanId(artisanId);
        if (existing != null) {
            photoProfilRepository.delete(existing);
        }

        // Sauvegarde DB
        PhotoProfil photoProfil = new PhotoProfil();
        photoProfil.setUrl("/" + UPLOAD_DIR + filename);
        photoProfil.setArtisan(artisan);

        PhotoProfil savedPhoto = photoProfilRepository.save(photoProfil);

        artisan.setPhotoProfil(savedPhoto);
        artisanRepository.save(artisan);

        return savedPhoto;
    }
}

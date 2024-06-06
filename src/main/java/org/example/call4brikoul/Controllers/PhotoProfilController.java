package org.example.call4brikoul.Controllers;

import org.example.call4brikoul.Services.PhotoProfilService;
import org.example.call4brikoul.models.PhotoProfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/artisans")
public class PhotoProfilController {

    private final PhotoProfilService photoProfilService;

    @Autowired
    public PhotoProfilController(PhotoProfilService photoProfilService) {
        this.photoProfilService = photoProfilService;
    }

    @PostMapping(
            value = "/{id}/photo-profil",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<PhotoProfil> uploadPhotoProfil(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) {
        try {
            PhotoProfil photoProfil = photoProfilService.uploadPhotoProfil(id, file);
            return ResponseEntity.ok(photoProfil);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

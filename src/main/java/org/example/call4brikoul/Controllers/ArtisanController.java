package org.example.call4brikoul.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.call4brikoul.DTO.ArtisanDTO;
import org.example.call4brikoul.Services.ArtisanService;
import org.example.call4brikoul.models.Artisan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/api/artisans")
@CrossOrigin("*")
public class ArtisanController {

    private final ArtisanService artisanService;

    @Autowired
    public ArtisanController(ArtisanService artisanService) {
        this.artisanService = artisanService;
    }

    @GetMapping
    public ResponseEntity<List<Artisan>> getAllArtisans() {
        List<Artisan> artisans = artisanService.getAllArtisans();
        return ResponseEntity.ok(artisans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artisan> getArtisanById(@PathVariable Long id) {
        Artisan artisan = artisanService.getArtisanById(id);
        if (artisan == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(artisan);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Artisan> createArtisan(
            @RequestPart("artisan") String artisanJson,
            @RequestPart(value = "photoProfile", required = false) MultipartFile photo
    ) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        ArtisanDTO dto = mapper.readValue(artisanJson, ArtisanDTO.class);

        Artisan artisan = artisanService.createArtisan(dto, photo);
        return ResponseEntity.status(HttpStatus.CREATED).body(artisan);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Artisan> updateArtisan(@PathVariable Long id, @RequestBody Artisan artisanDetails) {
        Artisan updatedArtisan = artisanService.updateArtisan(id, artisanDetails);
        if (updatedArtisan == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedArtisan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtisan(@PathVariable Long id) {
        artisanService.deleteArtisan(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ville/{villeNom}")
    public ResponseEntity<List<Artisan>> findArtisansByVille(@PathVariable String villeNom) {
        List<Artisan> artisans = artisanService.findArtisansByVille(villeNom);
        return ResponseEntity.ok(artisans);
    }

    @GetMapping("/specialite/{specialiteNom}")
    public ResponseEntity<List<Artisan>> findArtisansBySpecialite(@PathVariable String specialiteNom) {
        List<Artisan> artisans = artisanService.findArtisansBySpecialite(specialiteNom);
        return ResponseEntity.ok(artisans);
    }
}

package org.example.call4brikoul.Controllers;

import org.example.call4brikoul.Services.VilleService;
import org.example.call4brikoul.models.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/villes")
public class VilleController {

    private final VilleService villeService;

    @Autowired
    public VilleController(VilleService villeService) {
        this.villeService = villeService;
    }

    @GetMapping
    public List<Ville> getAllVilles() {
        return villeService.getAllVilles();
    }

    @GetMapping("/{id}")
    public Ville getVilleById(@PathVariable Long id) {
        return villeService.getVilleById(id);
    }

    @PostMapping
    public Ville createVille(@RequestBody Ville ville) {
        return villeService.createVille(ville);
    }

    @PutMapping("/{id}")
    public Ville updateVille(@PathVariable Long id, @RequestBody Ville villeDetails) {
        return villeService.updateVille(id, villeDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteVille(@PathVariable Long id) {
        villeService.deleteVille(id);
    }
}


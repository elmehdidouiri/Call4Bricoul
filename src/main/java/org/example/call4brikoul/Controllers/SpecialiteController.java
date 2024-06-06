package org.example.call4brikoul.Controllers;
import org.example.call4brikoul.Services.SpecialiteService;
import org.example.call4brikoul.models.Specialite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/specialites")
public class SpecialiteController {

    private final SpecialiteService specialiteService;

    @Autowired
    public SpecialiteController(SpecialiteService specialiteService) {
        this.specialiteService = specialiteService;
    }

    @GetMapping
    public List<Specialite> getAllSpecialites() {
        return specialiteService.getAllSpecialites();
    }

    @GetMapping("/{id}")
    public Specialite getSpecialiteById(@PathVariable Long id) {
        return specialiteService.getSpecialiteById(id);
    }

    @PostMapping
    public Specialite createSpecialite(@RequestBody Specialite specialite) {
        return specialiteService.createSpecialite(specialite);
    }

    @PutMapping("/{id}")
    public Specialite updateSpecialite(@PathVariable Long id, @RequestBody Specialite specialiteDetails) {
        return specialiteService.updateSpecialite(id, specialiteDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteSpecialite(@PathVariable Long id) {
        specialiteService.deleteSpecialite(id);
    }
}



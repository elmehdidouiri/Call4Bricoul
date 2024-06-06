package org.example.call4brikoul.Services;

import org.example.call4brikoul.Repository.VilleRepository;
import org.example.call4brikoul.models.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VilleService {

    private final VilleRepository villeRepository;

    @Autowired
    public VilleService(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }

    public List<Ville> getAllVilles() {
        return villeRepository.findAll();
    }

    public Ville getVilleById(Long id) {
        return villeRepository.findById(id).orElse(null);
    }

    public Ville createVille(Ville ville) {
        return villeRepository.save(ville);
    }

    public Ville updateVille(Long id, Ville villeDetails) {
        Ville ville = villeRepository.findById(id).orElse(null);
        if (ville == null) {
            return null;
        }
        ville.setNom(villeDetails.getNom());
        ville.setRegion(villeDetails.getRegion());
        return villeRepository.save(ville);
    }

    public void deleteVille(Long id) {
        villeRepository.deleteById(id);
    }
}




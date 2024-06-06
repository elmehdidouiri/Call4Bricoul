package org.example.call4brikoul.Services;

import org.example.call4brikoul.Repository.CategorieRepository;
import org.example.call4brikoul.Repository.SpecialiteRepository;
import org.example.call4brikoul.models.Specialite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class SpecialiteService {

    private final SpecialiteRepository specialiteRepository;
    private final CategorieRepository categorieRepository;

    @Autowired
    public SpecialiteService(SpecialiteRepository specialiteRepository, CategorieRepository categorieRepository) {
        this.specialiteRepository = specialiteRepository;
        this.categorieRepository = categorieRepository;
    }

    public List<Specialite> getAllSpecialites() {
        return specialiteRepository.findAll();
    }

    public Specialite getSpecialiteById(Long id) {
        return specialiteRepository.findById(id).orElse(null);
    }

    public Specialite createSpecialite(Specialite specialite) {
        return specialiteRepository.save(specialite);
    }

    public Specialite updateSpecialite(Long id, Specialite specialiteDetails) {
        Specialite specialite = specialiteRepository.findById(id).orElse(null);
        if (specialite == null) {
            return null;
        }
        specialite.setNom(specialiteDetails.getNom());
        specialite.setDescription(specialiteDetails.getDescription());
        specialite.setCategorie(specialiteDetails.getCategorie());
        return specialiteRepository.save(specialite);
    }

    public void deleteSpecialite(Long id) {
        specialiteRepository.deleteById(id);
    }
}




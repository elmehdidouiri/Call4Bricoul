package org.example.call4brikoul.Services;

import org.example.call4brikoul.Repository.CategorieRepository;
import org.example.call4brikoul.models.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategorieService {

    private final CategorieRepository categorieRepository;

    @Autowired
    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    public Categorie getCategorieById(Long id) {
        return categorieRepository.findById(id).orElse(null);
    }

    public Categorie createCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public Categorie updateCategorie(Long id, Categorie categorieDetails) {
        Categorie categorie = categorieRepository.findById(id).orElse(null);
        if (categorie == null) {
            return null;
        }
        categorie.setNom(categorieDetails.getNom());
        return categorieRepository.save(categorie);
    }

    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);
    }
}


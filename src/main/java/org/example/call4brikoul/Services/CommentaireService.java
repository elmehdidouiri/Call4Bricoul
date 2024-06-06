package org.example.call4brikoul.Services;

import org.example.call4brikoul.Repository.CommentaireRepository;
import org.example.call4brikoul.models.Commentaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentaireService {
    private final CommentaireRepository commentaireRepository;

    @Autowired
    public CommentaireService(CommentaireRepository commentaireRepository) {
        this.commentaireRepository = commentaireRepository;
    }

    public List<Commentaire> getAllCommentaires() {
        return commentaireRepository.findAll();
    }

    public List<Commentaire> getCommentairesByArtisanId(Long artisanId) {
        return commentaireRepository.findByArtisanId(artisanId);
    }

    public Commentaire getCommentaireById(Long id) {
        return commentaireRepository.findById(id).orElse(null);
    }

    public Commentaire createCommentaire(Commentaire commentaire) {
        return commentaireRepository.save(commentaire);
    }

    public Commentaire updateCommentaire(Long id, Commentaire commentaireDetails) {
        Commentaire commentaire = commentaireRepository.findById(id).orElse(null);
        if (commentaire == null) {
            return null;
        }
        commentaire.setContenu(commentaireDetails.getContenu());
        commentaire.setDate(commentaireDetails.getDate());
        return commentaireRepository.save(commentaire);
    }

    public void deleteCommentaire(Long id) {
        commentaireRepository.deleteById(id);
    }
}

package org.example.call4brikoul.Controllers;

import org.example.call4brikoul.Services.CommentaireService;
import org.example.call4brikoul.models.Commentaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/commentaires")
public class CommentaireController {

    private final CommentaireService commentaireService;

    @Autowired
    public CommentaireController(CommentaireService commentaireService) {
        this.commentaireService = commentaireService;
    }

    @GetMapping
    public List<Commentaire> getAllCommentaires() {
        return commentaireService.getAllCommentaires();
    }

    @GetMapping("/artisan/{artisanId}")
    public List<Commentaire> getCommentairesByArtisanId(@PathVariable Long artisanId) {
        return commentaireService.getCommentairesByArtisanId(artisanId);
    }

    @GetMapping("/{id}")
    public Commentaire getCommentaireById(@PathVariable Long id) {
        return commentaireService.getCommentaireById(id);
    }

    @PostMapping
    public Commentaire createCommentaire(@RequestBody Commentaire commentaire) {
        return commentaireService.createCommentaire(commentaire);
    }

    @PutMapping("/{id}")
    public Commentaire updateCommentaire(@PathVariable Long id, @RequestBody Commentaire commentaireDetails) {
        return commentaireService.updateCommentaire(id, commentaireDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteCommentaire(@PathVariable Long id) {
        commentaireService.deleteCommentaire(id);
    }
}

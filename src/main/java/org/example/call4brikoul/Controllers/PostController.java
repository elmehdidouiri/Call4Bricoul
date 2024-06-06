package org.example.call4brikoul.Controllers;

import org.example.call4brikoul.Services.PostService;
import org.example.call4brikoul.Services.ArtisanService;
import org.example.call4brikoul.models.Artisan;
import org.example.call4brikoul.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private ArtisanService artisanService;

    /**
     * Créer un post pour un artisan avec plusieurs photos
     * URL: POST /api/posts/{artisanId}
     * Form-data params:
     *  - titre: String
     *  - description: String
     *  - photos: fichiers (optionnel, multiple)
     */
    @PostMapping("/{artisanId}")
    public ResponseEntity<Post> createPost(
            @PathVariable Long artisanId,
            @RequestParam String titre,
            @RequestParam String description,
            @RequestParam(required = false) List<MultipartFile> photos
    ) throws IOException {

        // Récupérer l'artisan depuis la DB
        Artisan artisan = artisanService.getArtisanById(artisanId);
        if (artisan == null) {
            return ResponseEntity.notFound().build();
        }

        // Créer le post
        Post post = postService.createPost(artisan, titre, description, photos);
        return ResponseEntity.ok(post);
    }

    /**
     * Récupérer tous les posts d’un artisan
     * URL: GET /api/posts/{artisanId}
     */
    @GetMapping("/{artisanId}")
    public ResponseEntity<List<Post>> getPostsByArtisan(@PathVariable Long artisanId) {

        Artisan artisan = artisanService.getArtisanById(artisanId);
        if (artisan == null) {
            return ResponseEntity.notFound().build();
        }

        List<Post> posts = postService.getPostsByArtisan(artisanId);
        return ResponseEntity.ok(posts);
    }

    /**
     * Supprimer un post
     * URL: DELETE /api/posts/{postId}
     */
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}

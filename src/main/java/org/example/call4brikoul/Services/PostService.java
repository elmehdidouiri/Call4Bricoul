package org.example.call4brikoul.Services;

import org.example.call4brikoul.models.Artisan;
import org.example.call4brikoul.models.PhotoPost;
import org.example.call4brikoul.models.Post;
import org.example.call4brikoul.Repository.PostRepository;
import org.example.call4brikoul.Repository.PhotoPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PhotoPostRepository photoPostRepository;

    private final String uploadDir = "uploads/posts/";

    // Créer un post avec plusieurs photos
    public Post createPost(Artisan artisan, String titre, String description, List<MultipartFile> photos) throws IOException {
        Post post = new Post();
        post.setTitre(titre);
        post.setDescription(description);
        post.setArtisan(artisan);

        List<PhotoPost> photoPosts = new ArrayList<>();

        if (photos != null) {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            for (MultipartFile file : photos) {
                String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(file.getInputStream(), filePath);

                PhotoPost photoPost = new PhotoPost();
                photoPost.setUrl("/" + uploadDir + fileName);
                photoPost.setPost(post);
                photoPosts.add(photoPost);
            }
        }

        post.setPhotos(photoPosts);
        return postRepository.save(post);
    }

    // Lister les posts d’un artisan
    public List<Post> getPostsByArtisan(Long artisanId) {
        return postRepository.findByArtisanId(artisanId);
    }

    // Supprimer un post
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}

package org.example.call4brikoul.Repository;

import org.example.call4brikoul.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByArtisanId(Long artisanId);
}

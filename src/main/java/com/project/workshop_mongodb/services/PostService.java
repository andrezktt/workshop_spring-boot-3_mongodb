package com.project.workshop_mongodb.services;

import com.project.workshop_mongodb.domain.Post;
import com.project.workshop_mongodb.repository.PostRepository;
import com.project.workshop_mongodb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public Post findById(String id) {
        Optional<Post> post = repo.findById(id);
        return post.orElseThrow(() -> new ObjectNotFoundException("Post not found."));
    }
}

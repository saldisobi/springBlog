package com.saldi.spring.service;


import com.saldi.spring.dto.PostRequest;
import com.saldi.spring.model.Post;
import com.saldi.spring.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PostService {

    @Autowired
    private AuthService authService;

    @Autowired
    private PostRepository postRepository;

    @Transactional
    public void createPost(PostRequest postRequest) {
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        User user = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("No user found"));
        post.setUsername(user.getUsername());
        post.setCreatedOn(Instant.now());
        postRepository.save(post);
    }

    @Transactional
    public PostRequest readSinglePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("For id " + id));
        return mapFromPostToDto(post);
    }

    private PostRequest mapFromPostToDto(Post post) {
        PostRequest postDto = new PostRequest();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setUsername(post.getUsername());
        return postDto;
    }


    @Transactional
    public List<PostRequest> showAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapFromPostToDto).collect(toList());
    }

    private Post mapFromDtoToPost(PostRequest postRequest) {
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        User loggedInUser = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        post.setCreatedOn(Instant.now());
        post.setUsername(loggedInUser.getUsername());
        post.setUpdatedOn(Instant.now());
        return post;
    }
}

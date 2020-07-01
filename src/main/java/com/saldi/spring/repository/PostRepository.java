package com.saldi.spring.repository;

import com.saldi.spring.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PostRepository extends JpaRepository<Post, Long> {
}
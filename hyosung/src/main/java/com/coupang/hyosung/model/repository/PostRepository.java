package com.coupang.hyosung.model.repository;

import com.coupang.hyosung.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostByDeletedFalse();
}

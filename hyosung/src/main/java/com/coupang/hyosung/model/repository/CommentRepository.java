package com.coupang.hyosung.model.repository;

import com.coupang.hyosung.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByPostIdAndDeletedFalse(Long postId);
}

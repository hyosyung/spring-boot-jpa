package com.coupang.hyosung.service;

import com.coupang.hyosung.model.entity.Comment;
import com.coupang.hyosung.model.repository.CommentRepository;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository repository;

    @Transactional(readOnly = true)
    public List<Comment> getCommentsByPostId(Long postId) {
        return repository.findCommentsByPostIdAndDeletedFalse(postId);
    }

    @Transactional
    public void deleteCommentById(Long id) {
        Try.of(() -> repository.findById(id))
                .onFailure(e -> log.error("댓글의 정보를 조회하는데에 실패하였습니다.", e))
                .map(opt -> opt.orElse(null))
                .andThen(p -> {
                    p.setDeleted(true);
                    p.setModifiedDate(LocalDateTime.now());
                })
                .andThen(repository::save)
                .onFailure(e-> log.error("게시글을 삭제하지 못했습니다."));
    }

    @Transactional
    public void createComment(Long postId, String content) {
        Try.runRunnable(() -> repository.save(new Comment(postId, content)))
                .onFailure(e -> log.error("댓글을 저장하는데에 실패하였습니다.", e));
    }
}

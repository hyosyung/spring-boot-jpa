package com.coupang.hyosung.service;

import com.coupang.hyosung.model.entity.Post;
import com.coupang.hyosung.model.repository.PostRepository;
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
public class PostService {
    private final PostRepository repository;

    @Transactional(readOnly = true)
    public List<Post> getPostList() {
        return repository.findPostByDeletedFalse();
    }

    @Transactional(readOnly = true)
    public Post getPostById(Long id) {
        return repository.getById(id);
    }

    @Transactional
    public void deletePost(Long id) {
        Try.of(() -> repository.findById(id))
                .onFailure(e -> log.error("삭제할 게시글을 찾는데에 실패하였습니다.", e))
                .map(Optional::get)
                .map(post -> {
                    post.setDeleted(true);
                    post.setModifiedDate(LocalDateTime.now());
                    return post;
                })
                .peek(repository::save);
    }

    @Transactional
    public void createPost(String title, String content) {
        Try.runRunnable(() -> repository.save(new Post(title, content)))
                .onFailure(e -> log.error("게시글을 저장하는데에 실패하였습니다.", e))
                .recover(e -> null);
    }

    @Transactional
    public void updatePost(Long id, String title, String content) {
        Try.of(() -> repository.getById(id))
                .map(p -> {
                    p.setTitle(title);
                    p.setContent(content);
                    p.setModifiedDate(LocalDateTime.now());
                    return p;
                })
                .onFailure(e -> log.error("업데이트할 게시글을 찾는데에 실패하였습니다.", e))
                .peek(repository::save);
    }
}

package com.coupang.hyosung.service;

import com.coupang.hyosung.model.entity.Post;
import com.coupang.hyosung.model.repository.PostRepository;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private final PostRepository repository;

    @Transactional(readOnly = true)
    public List<Post> getPostList() {
        return repository.findAll();
    }

    @Transactional
    public void deletePost(Long id) throws Exception {
        Try.runRunnable(() -> repository.deleteById(id))
                .onFailure(e -> log.error("게시글을 삭제하는데에 실패하였습니다."))
                .getOrElseThrow(e -> new Exception(e.getMessage()));
    }

    @Transactional
    public void createPost(String title, String content) throws Exception {
        Try.runRunnable(() -> repository.save(new Post(title, content)))
                .onFailure(e -> log.error("게시글을 저장하는데에 실패하였습니다."))
                .getOrElseThrow(e -> new Exception(e.getMessage()));
    }

    @Transactional
    public void updatePost(Long id, String title, String content) throws Exception {
        Post post = repository.getById(id);
        post.setTitle(title);
        post.setContent(content);
        Try.runRunnable(() -> repository.save(post))
                .onFailure(e -> log.error("게시글을 업데이트하는데에 실패하였습니다. 게시글 id:{}", id))
                .getOrElseThrow(e -> new Exception(e.getMessage()));
    }
}
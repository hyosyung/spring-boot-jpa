package com.coupang.hyosung.service;

import com.coupang.hyosung.model.dto.CommentDto;
import com.coupang.hyosung.model.dto.PostDto;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;

import java.rmi.server.ExportException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MessageBoardService {
    private final PostService postService;
    private final CommentService commentService;

    public void deleteComment(Long id) {
        commentService.deleteCommentById(id);
    }

    public PostDto getPost(Long id) {
        return PostDto.of(postService.getPostById(id));
    }

    public void deletePost(Long id) {
        postService.deletePost(id);
    }

    public void createPost(String title, String content) {
        postService.createPost(title, content);
    }

    public void createComment(Long postId, String content) {
        commentService.createComment(postId, content);
    }

    public List<CommentDto> getCommentsByPostId(Long postId) {
        return commentService.getCommentsByPostId(postId).stream()
                .map(CommentDto::of)
                .collect(Collectors.toList());
    }

    public List<PostDto> getAllPosts() {
        return postService.getPostList().stream()
                .map(PostDto::of)
                .collect(Collectors.toList());
    }

    public void updatePost(Long id, String title, String content) {
        postService.updatePost(id, title, content);
    }
}

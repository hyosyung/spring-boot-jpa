package com.coupang.hyosung.service;

import com.coupang.hyosung.model.dto.CommentDto;
import com.coupang.hyosung.model.dto.PostDto;
import lombok.RequiredArgsConstructor;

import java.rmi.server.ExportException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MessageBoardService {
    private final PostService postService;
    private final CommentService commentService;

    public void deleteComment(Long id) throws Exception {
        commentService.deleteCommentById(id);
    }

    public void deletePost(Long id) throws Exception {
        postService.deletePost(id);
    }

    public void createPost(String title, String content) throws Exception {
        postService.createPost(title, content);
    }

    public void createComment(Long postId, String content) throws Exception {
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

    public void updatePost(Long id, String title, String content) throws Exception {
        postService.updatePost(id,title,content);
    }
}

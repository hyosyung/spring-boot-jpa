package com.coupang.hyosung.controller;

import com.coupang.hyosung.model.dto.PostDto;
import com.coupang.hyosung.service.MessageBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MessageBoardController {

    private final MessageBoardService boardService;

    @GetMapping("/get/post-list")
    public String getPostList(Model model) {
        model.addAttribute("posts", boardService.getAllPosts());
        return "index.html";
    }

    @PostMapping("/create/new-post")
    public String createNewPost(Model model, @RequestParam String title, @RequestParam String content) {
        boardService.createPost(title, content);
        model.addAttribute("posts", boardService.getAllPosts());
        return "index.html";
    }

    @GetMapping("/get/post")
    public String getPost(Model model, @RequestParam Long id) {
        model.addAttribute("post", boardService.getPost(id));
        model.addAttribute("comments", boardService.getCommentsByPostId(id));
        return "postDetail.html";
    }

    @PostMapping("/delete/post")
    public String deletePost(Model model, @RequestParam Long id) {
        boardService.deletePost(id);
        return "redirect:/get/post-list";
    }

    @PostMapping("/update/post")
    public String updatePost(Model model, @RequestParam Long id, @RequestParam String title, @RequestParam String content) {
        boardService.updatePost(id, title, content);
        return "redirect:/get/post?id=" + id;
    }

    @PostMapping("/create/new-comment")
    public String createComment(Model model, @RequestParam Long postId, @RequestParam String content) {
        boardService.createComment(postId, content);
        return "redirect:/get/post?id=" + postId;
    }

    @PostMapping("/delete/comment")
    public String deleteComment(Model model, @RequestParam Long id, @RequestParam Long postId) {
        boardService.deleteComment(id);
        return "redirect:/get/post?id=" + postId;
    }

    @GetMapping("/get/modify-page")
    public String getModifyPage(Model model, @RequestParam Long id) {
        model.addAttribute("post", boardService.getPost(id));
        return "modify.html";
    }

    @GetMapping("/get/create-page")
    public String getCreatePage() {
        return "create.html";
    }
}

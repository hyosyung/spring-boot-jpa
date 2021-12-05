package com.coupang.hyosung.model.dto;

import com.coupang.hyosung.model.entity.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static PostDto of(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setContent(post.getContent());
        postDto.setTitle(post.getTitle());
        postDto.setCreatedAt(post.getCreatedDate());
        postDto.setUpdatedAt(post.getModifiedDate());
        return postDto;
    }
}

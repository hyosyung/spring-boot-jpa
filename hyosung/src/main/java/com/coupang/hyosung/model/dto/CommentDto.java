package com.coupang.hyosung.model.dto;

import com.coupang.hyosung.model.entity.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String content;
    private LocalDateTime createdAt;

    public static CommentDto of(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(commentDto.getId());
        commentDto.setContent(comment.getContent());
        commentDto.setCreatedAt(comment.getCreatedDate());
        return commentDto;
    }
}

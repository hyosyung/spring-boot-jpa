package com.coupang.hyosung.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "deleted")
    private boolean deleted = false;

    public Comment(Long postId, String content) {
        this.content = content;
        this.postId = postId;
        this.createdDate = LocalDateTime.now();
    }

}

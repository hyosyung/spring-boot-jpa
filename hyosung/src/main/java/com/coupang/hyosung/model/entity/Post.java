package com.coupang.hyosung.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content", length = 50)
    @JoinColumn(name = "post_id")
    private String content;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Post> comments = new ArrayList<>();

    @Column(name = "deleted")
    private boolean deleted = false;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.createdDate = LocalDateTime.now();
    }

}

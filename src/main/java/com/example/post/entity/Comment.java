package com.example.post.entity;

import com.example.post.common.Timestamped;
import com.example.post.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_comment")
@Getter
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Comment(Post post, CommentRequestDto requestDto) {
        this.post = post;
        this.content = requestDto.getContent();
    }

    public void updateComment(CommentRequestDto requestDto) {
        this.content = requestDto.getContent();
    }
}

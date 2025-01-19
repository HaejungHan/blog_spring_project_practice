package com.example.post.dto;

import com.example.post.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private Long postId;
    private Long commentId;
    private String content;

    public CommentResponseDto(Long postId, Comment comment) {
        this.postId = comment.getPost().getId();
        this.commentId = comment.getId();
        this.content = comment.getContent();
    }
}

package com.example.post.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    @NotEmpty(message = "내용을 입력해주세요.")
    private String content;
}

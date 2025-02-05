package com.example.post.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {
    @NotEmpty(message = "제목을 입력하세요.")
    private String title;
    @NotEmpty(message = "내용을 입력하세요.")
    private String description;
}

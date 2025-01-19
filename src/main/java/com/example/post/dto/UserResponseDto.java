package com.example.post.dto;

import com.example.post.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private Long id;
    private String email;
    private String name;

    public UserResponseDto (User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
    }

}

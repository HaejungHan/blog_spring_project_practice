package com.example.post.entity;

import com.example.post.common.Timestamped;
import com.example.post.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_users")
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "name", nullable = false)
    private String name;

    public User(UserRequestDto requestDto) {
        this.email = requestDto.getEmail();
        this.name = requestDto.getName();
    }

    public void updateUser(UserRequestDto requestDto) {
        if(requestDto.getEmail() != null) {
            this.email = requestDto.getEmail();
        }
        if (requestDto.getName() != null) {
            this.name = requestDto.getName();
        }
    }
}

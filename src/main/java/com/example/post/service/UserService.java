package com.example.post.service;

import com.example.post.dto.UserRequestDto;
import com.example.post.dto.UserResponseDto;
import com.example.post.entity.User;
import com.example.post.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto createUser(UserRequestDto requestDto) {
        if(duplicatedEmail(requestDto.getEmail())) {
            throw  new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }
        User user = new User(requestDto);
        userRepository.save(user);
        return new UserResponseDto(user);
    }

    public UserResponseDto getUserById(Long id) {
        User user = findById(id);
        return new UserResponseDto(user);
    }

    public List<UserResponseDto> getAllUsers(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<User> users = userRepository.findAll(pageable);
        System.out.println(users);
        return users.stream().map(user -> new UserResponseDto(user)).toList();
    }

    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto requestDto) {
        User user = findById(id);
        if(duplicatedEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("중복된 사용자 입니다.");
        }
        user.updateUser(requestDto);
        userRepository.save(user);
        return new UserResponseDto(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }

    private boolean duplicatedEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));
    }
}

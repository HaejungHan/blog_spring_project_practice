package com.example.post.service;

import com.example.post.dto.PostRequestDto;
import com.example.post.dto.PostResponseDto;
import com.example.post.entity.Post;
import com.example.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post savedPost = postRepository.save(new Post(requestDto));
        return new PostResponseDto(savedPost);
    }

    public PostResponseDto getPostById(Long id) {
        Post findPost = findById(id);
        return new PostResponseDto(findPost);
    }


//    public List<PostResponseDto> getAllPosts() {
//        List<Post> postList = postRepository.findAll();
//        List<PostResponseDto> responseDtos = new ArrayList<>();
//        for(Post post : postList) {
//            responseDtos.add(new PostResponseDto(post));
//        } return responseDtos;
//    }

    public List<PostResponseDto> getAllPosts(int page, int pageSize) {
        Pageable pageRequest = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC,"createdAt"));
        Page<Post> postList = postRepository.findAll(pageRequest);

        return postList.stream().map(post -> new PostResponseDto(post)).toList();
    }


    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
        Post post = findById(id);
        post.updatePost(requestDto);
        postRepository.save(post);
        return new PostResponseDto(post);
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = findById(id);
        postRepository.delete(post);
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시물을 찾을 수 없습니다."));
    }

}

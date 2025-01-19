package com.example.post.service;

import com.example.post.dto.CommentRequestDto;
import com.example.post.dto.CommentResponseDto;
import com.example.post.entity.Comment;
import com.example.post.entity.Post;
import com.example.post.repository.CommentRepository;
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
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;

    @Transactional
    public CommentResponseDto createComment(Long postId, CommentRequestDto requestDto) {
        Post post = postService.findById(postId);
        Comment comment = new Comment(post, requestDto);
        commentRepository.save(comment);
        return new CommentResponseDto(comment.getPost().getId(), comment);
    }

    public List<CommentResponseDto> getAllCommentsByPostId(Long postId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
        Post post = postService.findById(postId);
        Page<Comment> commentList = commentRepository.findAllByPost(post, pageable);
        return commentList.stream().map(comment -> new CommentResponseDto(comment.getPost().getId(), comment)).toList();
    }

    public CommentResponseDto getCommentById(Long commentId) {
        Comment comment = findById(commentId);
        return new CommentResponseDto(comment.getPost().getId(), comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto) {
        Comment comment = findById(commentId);
        comment.updateComment(requestDto);
        commentRepository.save(comment);
        return new CommentResponseDto(comment.getPost().getId(), comment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = findById(commentId);
        commentRepository.delete(comment);
    }

    private Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다."));
    }
}

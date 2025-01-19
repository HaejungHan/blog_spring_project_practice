package com.example.post.controller;

import com.example.post.dto.CommentRequestDto;
import com.example.post.dto.CommentResponseDto;
import com.example.post.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable(value = "id") Long postId, @Valid @RequestBody CommentRequestDto requestDto) {
        return new ResponseEntity<>(commentService.createComment(postId, requestDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentResponseDto>> getAllCommentsByPostId(
            @PathVariable(value = "id") Long postId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return new ResponseEntity<>(commentService.getAllCommentsByPostId(postId, page, pageSize), HttpStatus.OK);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDto> getCommentById(@PathVariable(value = "id") Long commentId) {
        return new ResponseEntity<>(commentService.getCommentById(commentId), HttpStatus.OK);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable(value = "id") Long commentId, @Valid @RequestBody CommentRequestDto requestDto) {
        return new ResponseEntity<>(commentService.updateComment(commentId, requestDto), HttpStatus.OK);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable(value = "id") Long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

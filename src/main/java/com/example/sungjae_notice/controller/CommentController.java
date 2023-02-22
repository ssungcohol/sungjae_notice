package com.example.sungjae_notice.controller;

import com.example.sungjae_notice.dto.CommentMessageDto;
import com.example.sungjae_notice.dto.CommentRequestDto;
import com.example.sungjae_notice.dto.CommentResponseDto;
import com.example.sungjae_notice.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/api/notice/{noticeId}/comment")
    public CommentResponseDto creatComment(@PathVariable Long noticeId, @RequestBody CommentRequestDto comRequestDto, HttpServletRequest request) {

        return commentService.creatComment(noticeId, comRequestDto, request);
    }

    // 댓글 수정
    @PutMapping("api/notice/{noticeId}/comment/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long noticeId, @PathVariable Long commentId, @RequestBody CommentRequestDto comRequestDto, HttpServletRequest request) {

        return commentService.updateComment(noticeId, commentId, comRequestDto, request);
    }


    // 댓글 수정
    @DeleteMapping("/api/notice/{noticeId}/comment/{commentId}")
    public CommentMessageDto deleteComment(@PathVariable Long noticeId, @PathVariable Long commentId, @RequestBody CommentRequestDto comRequestDto, HttpServletRequest request) {

        return commentService.deleteComment(noticeId, commentId, comRequestDto, request);
    }

}
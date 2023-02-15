package com.example.sungjae_notice.controller;

import com.example.sungjae_notice.dto.NoticeMessageDto;
import com.example.sungjae_notice.dto.NoticeRequestDto;
import com.example.sungjae_notice.dto.NoticeResponseDto;
import com.example.sungjae_notice.entity.Notice;
import com.example.sungjae_notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService; // NoticeSercive와 연결하여 불러오기~(연결)


    // 게시글 목록 조회
    @GetMapping("/api/notices")
    public List<NoticeResponseDto> getNotice() {
        return noticeService.getNotice();
    }

    // 게시글 작성(jwt)
    @PostMapping("/api/notice")
    public NoticeResponseDto createProduct(@RequestBody NoticeRequestDto requestDto, HttpServletRequest request) {
        // 응답 보내기
        return noticeService.createNotice(requestDto, request);
    }

    // 회원(토큰) 선택 게시글 조회
    @GetMapping("/api/notice/{id}")
    public NoticeResponseDto getNotice(@PathVariable Long id) {
        return noticeService.getNotice(id);
    }

    // 회원(토큰) 게시글 수정
    @PutMapping("/api/notice/{id}")
    public NoticeResponseDto updateNotice(@PathVariable Long id, @RequestBody NoticeRequestDto requestDto, HttpServletRequest request) {
        // 메세지 반환이 아닌 수정된 게시글을 반환해야하기에 NoticeResponseDto로 타입 변환
        return noticeService.updateNotice(id, requestDto, request);
    }

    // 회원(토큰) 게시글 삭제
    @DeleteMapping("/api/notice/{id}")
    // 삭제 Delete 방식 매핑
    public NoticeMessageDto deleteNotice(@PathVariable Long id, @RequestBody NoticeRequestDto requestDto, HttpServletRequest request) {
        return noticeService.deleteNotice(id, requestDto, request);
    }

}

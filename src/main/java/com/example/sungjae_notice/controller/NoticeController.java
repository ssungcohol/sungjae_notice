package com.example.sungjae_notice.controller;

import com.example.sungjae_notice.dto.NoticeRequestDto;
import com.example.sungjae_notice.dto.NoticeResponseDto;
import com.example.sungjae_notice.entity.Notice;
import com.example.sungjae_notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.web.bind.annotation.*;

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

    // 게시글 작성
    @PostMapping("/api/notice")
    public NoticeResponseDto createNotice(@RequestBody NoticeRequestDto requestDto) { //createNotice 메서드 생성 클라이언트에서 가지고 온 값 사용을 위해 파라미터입력
        return noticeService.createNotice(requestDto); // 게시글을 작성하면 바로 게시글을 클라로 보내줄 것입니다!~
    }

    //선택 게시글 조회
    @GetMapping("/api/notice/{id}")
    public NoticeResponseDto getNotice(@PathVariable Long id) {
        return noticeService.getNotice(id);
    }

    // 선택 게시글 수정
    @PutMapping("/api/notice/{id}")
    public NoticeResponseDto update(@PathVariable Long id, @RequestBody NoticeRequestDto requestDto) {
        return noticeService.update(id, requestDto);
    }

    @DeleteMapping("/api/notice/{id}")
    public boolean delete(@PathVariable Long id, @RequestBody NoticeRequestDto requestDto) {
        return noticeService.delete(id, requestDto);
    }






    // 게시글 수정
//    @PutMapping("/api/notice/{id}")
//    public Long updateNotice(@PathVariable Long id, @RequestBody NoticeRequestDto requestDto) {
//        return noticeService.update(id, requestDto);
//    }

//    @PutMapping("/api/notice/{id}")
//    public Long updateNotice(@PathVariable Long id, @RequestBody String title, String username, String contents, String password) {
//        return noticeService.update(id, title, username, contents, password);
//    }
}

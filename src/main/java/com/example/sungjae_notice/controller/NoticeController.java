package com.example.sungjae_notice.controller;

// 요청을 받기 위한 컨드롤러 (서버)

import com.example.sungjae_memo.dto.NoticeRequestDto;
import com.example.sungjae_memo.entity.Notice;
import com.example.sungjae_memo.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService; // NoticeSercive와 연결하여 불러오기~(연결)

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index"); // html이 반환되는 부분 ModelAndView 를 통해 반환해줄 index 대입
    }

    @PostMapping("/api/notice")
    public Notice createNotice(@RequestBody NoticeRequestDto noticeRequestDto) { //createNotice 메서드 생성 클라이언트에서 가지고 온 값 사용을 위해 파라미터입력
        return noticeService.createNotice(noticeRequestDto); // 게시글을 작성하면 바로 게시글을 클라로 보내줄 것입니다!~
    }

    @GetMapping("/api/notice")
    public List<Notice> getNotice() {
        return noticeService.getNotice();
    }
}

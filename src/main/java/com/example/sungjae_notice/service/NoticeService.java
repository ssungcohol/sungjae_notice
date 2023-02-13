package com.example.sungjae_notice.service;

import com.example.sungjae_notice.dto.NoticeMessageDto;
import com.example.sungjae_notice.dto.NoticeRequestDto;
import com.example.sungjae_notice.dto.NoticeResponseDto;
import com.example.sungjae_notice.entity.Notice;
import com.example.sungjae_notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service  // 이곳은 DB와 연결을 해주는 서비스다! 라는걸 알려줌
@RequiredArgsConstructor
public class NoticeService {  // 이곳에서 데이터베이스와 연결을 해줌

    private final NoticeRepository noticeRepository;  // 데이터와 연결하는 부분인 NoticeRepository를 사용할 수 있도록 추가

    // 게시글 목록 조회
    public List<NoticeResponseDto> getNotice() {
        List <Notice> noticeList = noticeRepository.findAllByOrderByModifiedAtDesc(); // 리스트 형태로 내림차순 정리
        List <NoticeResponseDto> noticeResponseDtoList = new ArrayList<>(); // 리스트 생성
        for (Notice notice : noticeList) {
            NoticeResponseDto tmp = new NoticeResponseDto(notice);
            noticeResponseDtoList.add(tmp);
        }
        return noticeResponseDtoList;
    }

    // 게시글 작성
    @Transactional
    public NoticeResponseDto createNotice(NoticeRequestDto requestDto){
        Notice notice = new Notice(requestDto); // 객체 생성
        noticeRepository.save(notice);
        NoticeResponseDto noticeResponseDto = new NoticeResponseDto(notice);
        return noticeResponseDto;
    }

    // 선택 게시글 조회
    @Transactional (readOnly = true)
    public NoticeResponseDto getNotice(Long id) {
        Notice notice = noticeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.") //DB에 존재하지 않으면
        );
        NoticeResponseDto noticeResponseDto = new NoticeResponseDto(notice);
        return noticeResponseDto;
    }

    // 게시글 수정 및 비밀번호 조회
    @Transactional
    public NoticeMessageDto update(Long id, NoticeRequestDto requestDto) {  // 컨트롤러랑 타입 맞추는거 잊지말기
        Notice notice = noticeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (requestDto.getPassword().equals(notice.getPassword())) {  //비밀번호가 일치하면
            notice.update(requestDto);
            return new NoticeMessageDto("수정 성공");  //생성자를 생성하여 NoticeMessageDTO에 전달
        }
        return new NoticeMessageDto("수정 못하쥬~~ 비밀번호 틀렸쥬~~");
    }

    // 게시글 삭제
    @Transactional
    public NoticeMessageDto delete(Long id, NoticeRequestDto requestDto) { //delete라는 메소드
        Notice notice = noticeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.") //예외처리
        );
        if (requestDto.getPassword().equals(notice.getPassword())) {  //비밀번호가 일치하면
            noticeRepository.deleteById(id); // 게시물을 삭제하거라~~
            return new NoticeMessageDto("삭제 성공");
        }
        else {
            return new NoticeMessageDto("맘대로 못지우지~~ 어딜가~~");
        }
    }
}

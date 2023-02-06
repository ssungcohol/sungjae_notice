package com.example.sungjae_notice.service;

import com.example.sungjae_memo.dto.NoticeRequestDto;
import com.example.sungjae_memo.entity.Notice;
import com.example.sungjae_memo.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service  // 이곳은 DB와 연결을 해주는 서비스다! 라는걸 알려줌
@RequiredArgsConstructor
public class NoticeService {  // 이곳에서 데이터베이스와 연결을 해줌

    private final NoticeRepository noticeRepository;  // 데이터와 연결하는 부분인 NoticeRepository를 사용할 수 있도록 추가

    @Transactional
    public Notice createNotice(NoticeRequestDto requestDto){
        Notice notice = new Notice(requestDto);
        noticeRepository.save(notice);
        return notice;
    }

    @Transactional(readOnly = true)
    public List<Notice> getNotice() {
        return noticeRepository.findAllByOrderByModifiedAtDesc(); //노티스 레포에서 저놈을 가지고와서 내림차순으로 보여주마
    }
}

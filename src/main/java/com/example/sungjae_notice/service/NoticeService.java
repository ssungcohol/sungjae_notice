package com.example.sungjae_notice.service;

import com.example.sungjae_notice.dto.NoticeMessageDto;
import com.example.sungjae_notice.dto.NoticeRequestDto;
import com.example.sungjae_notice.dto.NoticeResponseDto;
import com.example.sungjae_notice.entity.Notice;
import com.example.sungjae_notice.entity.User;
import com.example.sungjae_notice.entity.UserRoleEnum;
import com.example.sungjae_notice.jwt.JwtUtil;
import com.example.sungjae_notice.repository.NoticeRepository;
import com.example.sungjae_notice.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Service  // 이곳은 DB와 연결을 해주는 서비스다! 라는걸 알려줌
@RequiredArgsConstructor
public class NoticeService {  // 이곳에서 데이터베이스와 연결을 해줌

    private final NoticeRepository noticeRepository;  // 데이터와 연결하는 부분인 NoticeRepository를 사용할 수 있도록 추가

    private final JwtUtil jwtUtil;

    private final UserRepository userRepository;

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
    public NoticeResponseDto createNotice(NoticeRequestDto requestDto, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 관심상품 추가 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            // 요청받은 DTO 로 DB에 저장할 객체 만들기
            Notice notice = noticeRepository.saveAndFlush(new Notice(requestDto, user));

            return new NoticeResponseDto(notice);
        } else {
            return null;
        }
    }


    // 선택 게시글 조회
    @Transactional (readOnly = true)
    public NoticeResponseDto getNotice(Long id) {
        Notice notice = noticeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        NoticeResponseDto noticeResponseDto = new NoticeResponseDto(notice);
        return noticeResponseDto;
    }



    // 게시글 수정 및 비밀번호 조회
    @Transactional
    public NoticeResponseDto updateNotice(Long id, NoticeRequestDto requestDto, HttpServletRequest request) {
        // NoticeResponseDto 타입! Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 관심상품 최저가 업데이트 가능
        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Notice notice = noticeRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new NullPointerException("해당 게시물이 존재하지 않습니다.")
            );

            notice.update(requestDto);

            return new NoticeResponseDto(notice); // 수정된 게시글 반환

        } else {
            return null;
        }
    }


    // 게시글 삭제
    @Transactional
    public NoticeMessageDto deleteNotice(Long id, NoticeRequestDto requestDto, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 관심상품 최저가 업데이트 가능
        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Notice notice = noticeRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new NullPointerException("해당 게시물이 존재하지 않습니다.")
            );

            noticeRepository.deleteById(id);

            return new NoticeMessageDto("삭제 성공");

        } else {
            return new NoticeMessageDto("삭제 실패");
        }
    }


}

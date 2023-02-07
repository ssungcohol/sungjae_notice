package com.example.sungjae_notice.entity;

import com.example.sungjae_notice.dto.NoticeRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 1. JPA
// 데입터베이스와 자바 객체의 맵핑을 위한 기술

@Getter
@Entity
@NoArgsConstructor
public class Notice extends Timestamped {
    @Id  // JPA에서 기본키를 나타는 필드에 사용
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String password;

    //생성자
    public Notice(NoticeRequestDto requestDto){  // 서버에서 DB 저장에 필요한 값을 만들어주는 곳
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.password = requestDto.getPassword();
    }

    public void update(NoticeRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
    }

}

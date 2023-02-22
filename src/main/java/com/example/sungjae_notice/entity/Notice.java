package com.example.sungjae_notice.entity;

import com.example.sungjae_notice.dto.NoticeRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

//    @Column(nullable = false)  토큰 사용
//    private String username;

    @Column(nullable = false)
    private String contents;

//    @Column(nullable = false)  토큰 사용으로 사용자 정보 수정/삭제하기 위해 사용
//    private String password;

    // 사용자 한 명에 게시글 여러개
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    // 게시글 하나에 댓글 여러개
    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL)
    List<Comment> comments = new ArrayList<>(); // 댓글 가져오기

    //생성자
    public Notice(NoticeRequestDto requestDto, User user){
        // 서버에서 DB 저장에 필요한 값을 만들어주는 곳
//        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
//        this.password = requestDto.getPassword();
        this.user = user;
    }

    public void update(NoticeRequestDto requestDto) {
//        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
    }

}

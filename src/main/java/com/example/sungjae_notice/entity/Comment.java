package com.example.sungjae_notice.entity;

import com.example.sungjae_notice.dto.CommentRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped {

    // 댓글 id PK
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "commentId")
    private Long id;


    // 댓글 내용
    @Column(nullable = false)
    private String comment;

    // 회원 id
    @JsonIgnore  //  순환참조 방지제 => 이분 때문에 사용자정보 다 가지고 왔음
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    // 게시물과 연관관계 매핑
    @JsonIgnore  // 순환참조 방지제
    @ManyToOne
    @JoinColumn(name = "notice_id")
    private Notice notice;

    // 댓글 작성 시
    public Comment(CommentRequestDto requestDto, User user, Notice notice) {
        this.comment = requestDto.getComment(); //Getter 필요?
        this.user = user;
        this.notice = notice;
    }

    // 댓글 수정 시
    public void update(CommentRequestDto requestDto) {
        this.comment = requestDto.getComment();
    }
}

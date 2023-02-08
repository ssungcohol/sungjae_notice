package com.example.sungjae_notice.dto;

import com.example.sungjae_notice.entity.Notice;
import lombok.Getter;

import java.time.LocalDateTime;

// DTO 반환이라는 과제 조건이 있음.
//

@Getter
public class NoticeResponseDto {
    private Long id;
    private String username;
    private String title;
    private String contents;
//    private String password;
    private LocalDateTime createdat;
    private LocalDateTime modifiedat;

    public NoticeResponseDto(Notice notice) {
        this.id = notice.getId();
        this.username = notice.getUsername();
        this.title = notice.getTitle();
        this.contents = notice.getContents();
//        this.password = notice.getPassword();
        this.createdat = notice.getCreatedAt();
        this.modifiedat = notice.getModifiedAt();
    }
}

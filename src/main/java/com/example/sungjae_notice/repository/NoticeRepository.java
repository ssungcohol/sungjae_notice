package com.example.sungjae_notice.repository;


import com.example.sungjae_notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Jpa레포를 상속받아 DB와 연결
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findAllByOrderByModifiedAtDesc(); // 데이터를 시간에 따라 내림차순으로 정리해줌
}

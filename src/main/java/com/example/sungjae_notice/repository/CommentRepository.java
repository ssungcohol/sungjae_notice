package com.example.sungjae_notice.repository;

import com.example.sungjae_notice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByOrderByModifiedAtDesc();
    // 뭔 말도 안되는 findAllByOrderByCommentModifiedAtDesc() 라는 걸 만들어서 쓰고있었음

}

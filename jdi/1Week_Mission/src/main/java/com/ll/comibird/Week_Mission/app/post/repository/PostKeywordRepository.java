package com.ll.comibird.Week_Mission.app.post.repository;

import com.ll.comibird.Week_Mission.app.post.entity.PostKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostKeywordRepository extends JpaRepository<PostKeyword, Long> {
    Optional<PostKeyword> findByContent(String postKeywordContent);
}

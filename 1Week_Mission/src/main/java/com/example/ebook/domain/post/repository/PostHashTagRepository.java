package com.example.ebook.domain.post.repository;

import com.example.ebook.domain.post.entities.PostHashTag;
import com.example.ebook.domain.post.entities.PostKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostHashTagRepository extends JpaRepository<PostHashTag, Long> {
}

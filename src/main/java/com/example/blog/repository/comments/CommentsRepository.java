package com.example.blog.repository.comments;

import com.example.blog.model.comments.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments,Long> , CommentsCustomRepository{
}

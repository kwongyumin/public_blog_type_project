package com.example.blog.repository.comments;

import com.example.blog.model.blog.Blog;
import com.example.blog.model.comments.Comments;

import java.util.List;
import java.util.Optional;

public interface CommentsCustomRepository {

    Optional<List<Comments>> findCommentsListByBlog(Blog blog);
}

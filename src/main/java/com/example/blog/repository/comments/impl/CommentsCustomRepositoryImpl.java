package com.example.blog.repository.comments.impl;

import com.example.blog.model.blog.Blog;
import com.example.blog.model.comments.Comments;
import com.example.blog.model.comments.QComments;
import com.example.blog.model.comments.WriteType;
import com.example.blog.repository.comments.CommentsCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentsCustomRepositoryImpl implements CommentsCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<List<Comments>> findCommentsListByBlog(Blog blog) {
        List<Comments> commentsList = queryFactory
                .selectFrom(QComments.comments)
                .where(QComments.comments.writeType.eq(WriteType.Blog)
                        .and(QComments.comments.writeTypeId.eq(blog.getId()))
                        .and(QComments.comments.userId.eq(blog.getUserId())))
                .fetch();

        return Optional.ofNullable(commentsList);
    }
}

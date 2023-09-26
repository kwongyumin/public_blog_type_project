package com.example.blog.repository.blog.impl;

import com.example.blog.dto.blog.BlogResponseDto;
import com.example.blog.dto.category.CategoryResponseDto;
import com.example.blog.dto.comments.CommentsResponseDto;
import com.example.blog.model.blog.Blog;
import com.example.blog.model.blog.QBlog;
import com.example.blog.model.category.Category;
import com.example.blog.model.category.QCategory;
import com.example.blog.model.comments.Comments;
import com.example.blog.model.comments.QComments;
import com.example.blog.model.comments.WriteType;
import com.example.blog.repository.blog.BlogCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BlogCustomRepositoryImpl implements BlogCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<BlogResponseDto.FindBlogDetail> findBlogDetailByBlogId(Long blogId) {

        Blog blog = queryFactory
                .selectFrom(QBlog.blog)
                .where(QBlog.blog.id.eq(blogId))
                .fetchOne();

        return Optional.ofNullable(blog != null ? entityToFindBlogDetailDto(blog) : null);

    }

    @Override
    public Optional<List<BlogResponseDto.FindBlog>> findBlogListByCategoryId(Long categoryId, int page, int size) {

        List<Blog> blogList = queryFactory
                .selectFrom(QBlog.blog)
                .where(QBlog.blog.category.id.eq(categoryId))
                .orderBy(QBlog.blog.modTime.desc())
                .offset(page * size)
                .limit(size)
                .fetch();

        return Optional.ofNullable(!CollectionUtils.isEmpty(blogList) ? entityToFindBlogDtoList(blogList) : null);

    }

    @Override
    public Optional<List<BlogResponseDto.FindBlog>> findLatestBlogListByUserId(Long userId) {

        List<Blog> latestBlogList = queryFactory
                .selectFrom(QBlog.blog)
                .where(QBlog.blog.userId.eq(userId))
                .orderBy(QBlog.blog.modTime.desc())
                .limit(4)
                .fetch();

        return Optional.ofNullable(!CollectionUtils.isEmpty(latestBlogList) ? entityToFindBlogDtoList(latestBlogList) : null);
    }


    /*******************************************************************************************************************
     * DESC : entity -> dto transferMethod
     *******************************************************************************************************************/

    private List<CommentsResponseDto.FindCommentsDetail> entityToFindCommentsDetailDto(Blog blog) {
        List<CommentsResponseDto.FindCommentsDetail> targetList = new ArrayList<>();
        // 페이지내의 댓글을 조회한다.
        List<Comments> commentsList = queryFactory
                .selectFrom(QComments.comments)
                .where(QComments.comments.writeType.eq(WriteType.Blog)
                        .and(QComments.comments.writeTypeId.eq(blog.getId()))
                        .and(QComments.comments.userId.eq(blog.getUserId())))
                .fetch();

       if (!CollectionUtils.isEmpty(commentsList)) {
           commentsList.forEach(comments -> {
               CommentsResponseDto.FindCommentsDetail target = CommentsResponseDto.FindCommentsDetail.builder()
                       .commentsId(comments.getId())
                       .writeTypeId(comments.getWriteTypeId())
                       .userId(comments.getUserId())
                       .contents(comments.getContents())
                       .build();
               targetList.add(target);
           });
       }
        return targetList;
    }

    private BlogResponseDto.FindBlogDetail entityToFindBlogDetailDto(Blog blog) {
        return BlogResponseDto.FindBlogDetail.builder()
                .userId(blog.getUserId())
                .blogId(blog.getId())
                .blogTitle(blog.getBlogTitle())
                .blogContents(blog.getBlogContents())
                .commentsList(entityToFindCommentsDetailDto(blog))
                .build();
    }

    private List<BlogResponseDto.FindBlog> entityToFindBlogDtoList(List<Blog> blogList) {
        List<BlogResponseDto.FindBlog> targetList = new ArrayList<>();
        blogList.forEach(blog -> {
            BlogResponseDto.FindBlog target = BlogResponseDto.FindBlog.builder()
                    .userId(blog.getUserId())
                    .categoryId(blog.getId())
                    .blogId(blog.getId())
                    .blogTitle(blog.getBlogTitle())
                    .build();
            targetList.add(target);
        });
        return targetList;
    }
}

package com.springboot.blog.service;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long PostId,CommentDto commentDto);
    List<CommentDto> getAllcomment(long id);
    CommentDto getCommentById(long postId,long commentId);
    CommentDto updateCommentById(long postId,long commentId,CommentDto commentDto);
}

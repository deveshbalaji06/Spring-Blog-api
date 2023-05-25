package com.springboot.blog.controller;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.service.CommentService;
import com.springboot.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<PostDto> createComment(@PathVariable(name ="postId") long postid, @RequestBody CommentDto commentDto)
    {
        return  new ResponseEntity(commentService.createComment(postid,commentDto), HttpStatus.CREATED);
    }
    @GetMapping("posts/{postId}/comments")
    public List<CommentDto> getAllCommentd(@PathVariable(name = "postId") long postId)
    {
        return commentService.getAllcomment(postId);
    }

    @GetMapping("posts/{postId}/comments/{id}")
    public CommentDto getAllCommentdById(@PathVariable(name = "postId") long postId,@PathVariable(name="id") long id)
    {
        return commentService.getCommentById(postId,id);
    }

    @PutMapping("posts/{postId}/comments/{id}")
    public CommentDto updateCommentdById(@PathVariable(name = "postId") long postId,@PathVariable(name="id") long id,@RequestBody CommentDto commentDto)
    {
        return commentService.updateCommentById(postId,id,commentDto);
    }

}

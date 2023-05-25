package com.springboot.blog.service.impls;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    public CommentServiceImpl(CommentRepository commentRepository,PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository=postRepository;

    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment=maptoEntity(commentDto);
        Post post= postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
       comment.setPost(post);
       Comment newComent=commentRepository.save(comment);
       CommentDto postResponse=mapToDto(newComent);

        return postResponse;
    }

    @Override
    public List<CommentDto> getAllcomment(long id) {

      List<Comment> comments=commentRepository.findByPostId(id);
         return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());

    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        Post post= postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));

        Comment comment=commentRepository.findById(commentId).orElseThrow(
                ()-> new ResourceNotFoundException("Comment","id",commentId));
        return mapToDto(comment);
    }

    @Override
    public CommentDto updateCommentById(long postId, long commentId, CommentDto commentReq) {
        Post post= postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));

        Comment comment=commentRepository.findById(commentId).orElseThrow(
                ()-> new ResourceNotFoundException("Comment","id",commentId));
        comment.setEmail(commentReq.getEmail());
        comment.setId(commentReq.getId());
        comment.setBody(commentReq.getBody());
        Comment updatedComment=commentRepository.save(comment);
        return mapToDto(updatedComment);
    }


    public List<CommentDto> getAllcommentById(long id) {

        List<Comment> comments=commentRepository.findByPostId(id);
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());

    }

    //convert entity to dto
    private CommentDto mapToDto(Comment comment)
    {
        CommentDto commentDto1=new CommentDto();
        commentDto1.setId(comment.getId());
        commentDto1.setName(comment.getName());
        commentDto1.setEmail(comment.getEmail());
        commentDto1.setBody(comment.getBody());
        return commentDto1;
    }
    //Convert DTO to entity
    private Comment maptoEntity(CommentDto commentDto)
    {
        Comment comment=new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        return  comment;
    }
}

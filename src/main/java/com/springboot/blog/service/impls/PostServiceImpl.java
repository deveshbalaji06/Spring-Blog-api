package com.springboot.blog.service.impls;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
  private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto)
    {

        //convert DTO to entity;
        Post post=new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());

       Post postResponse= postRepository.save(post);
       //convert entity to DTO
        PostDto postDto1=new PostDto();
        postDto1.setId(postResponse.getId());
        postDto1.setTitle(postResponse.getTitle());
        postDto1.setContent(postResponse.getContent());
        postDto1.setDescription(postResponse.getDescription());



        return  postDto1;
    }
}

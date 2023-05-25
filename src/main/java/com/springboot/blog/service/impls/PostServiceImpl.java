package com.springboot.blog.service.impls;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        Post post=maptoEntity(postDto);
        //save the post to database
       Post newPost= postRepository.save(post);
       //convert entity to DTO
      PostDto postResponse=mapToDto(newPost);

        return  postResponse;
    }

    @Override
    public List<PostDto> getAllposts(int pageNo,int pageSize) {


        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Post> posts=postRepository.findAll( pageable);
        List<Post> listOfPosts=posts.getContent();
        return listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

    }

    @Override
    public PostDto getPostById(long id) {
        Post post= postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));

        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post=postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        post.setTitle(postDto.getTitle());
       System.out.println(postDto.getTitle());
        Post updatedPost=postRepository.save(post);
        return  mapToDto(updatedPost);
    }

    @Override
    public void deletePost(long id) {

        try {
            postRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ;
    }


    //convert entity to dto
    private PostDto mapToDto(Post post)
    {
        PostDto postDto1=new PostDto();
        postDto1.setId(post.getId());
        postDto1.setTitle(post.getTitle());
        postDto1.setContent(post.getContent());
        postDto1.setDescription(post.getDescription());
        return postDto1;
    }
    //Convert DTO to entity
    private Post maptoEntity(PostDto postDto)
    {
        Post post=new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());

        return  post;
    }
}

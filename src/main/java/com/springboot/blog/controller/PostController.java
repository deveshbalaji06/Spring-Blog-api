package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto)
    {

      return  new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
    @GetMapping
    public List<PostDto> getAllpost()
    {
         return postService.getAllposts();
    }
    @GetMapping("/{id}")

    public ResponseEntity<PostDto> getPostById(@PathVariable(name ="id") long id )
    {
        return ResponseEntity.ok(postService.getPostById(id));

    }
    @PutMapping ("/{id}")
     public ResponseEntity<PostDto> updatedPost(@RequestBody PostDto postDto,@PathVariable(name="id") long id )
     {
         return ResponseEntity.ok(postService.updatePost(postDto,id));
     }
     @DeleteMapping("/{id}")
     public  ResponseEntity<String> deletePost(@PathVariable(name="id") long id)
     {
         postService.deletePost(id);
         return ResponseEntity.ok("Post delete succefully");
     }

}

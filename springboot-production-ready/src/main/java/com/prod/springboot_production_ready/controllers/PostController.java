package com.prod.springboot_production_ready.controllers;

import com.prod.springboot_production_ready.dto.PostDto;
import com.prod.springboot_production_ready.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/posts")
public class PostController {

    private final PostService postService;



    @GetMapping
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }

    @PostMapping
    public  PostDto createNewPost(@RequestBody PostDto createPost){
        return  postService.createNewPost(createPost);
    }

    @GetMapping(path = "/{postId}")
    public PostDto getPostById(@PathVariable Long postId){
         return postService.getPostById(postId);

    }

    @PutMapping(path = "/{postId}")
    public PostDto updatePost(@RequestBody PostDto toUpdate ,@PathVariable Long postId){
        return postService.updatePost(toUpdate,postId);
    }
}

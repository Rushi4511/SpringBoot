package com.prod.springboot_production_ready.services;

import com.prod.springboot_production_ready.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService{


    List<PostDto> getAllPosts();

    public PostDto getPostById(Long id);

    public PostDto updatePost(PostDto toUpdate ,Long id);

    public PostDto createNewPost(PostDto toCreatePostDto);


}

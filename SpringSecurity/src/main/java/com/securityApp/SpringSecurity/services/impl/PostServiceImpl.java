package com.securityApp.SpringSecurity.services.impl;

import com.securityApp.SpringSecurity.dto.PostDTO;
import com.securityApp.SpringSecurity.entities.PostEntity;
import com.securityApp.SpringSecurity.entities.User;
import com.securityApp.SpringSecurity.exceptions.ResourceNotFoundException;
import com.securityApp.SpringSecurity.repositories.PostRepository;
import com.securityApp.SpringSecurity.services.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDTO> getAllPosts() {

        return postRepository.findAll().stream().map(postEntity -> modelMapper.map(postEntity,PostDTO.class)).toList();

    }

    @Override
    public PostDTO getPostById(Long id) {

        User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.info("User {}",SecurityContextHolder.getContext().getAuthentication().getPrincipal());

 //       log.info("",(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return modelMapper.map( postRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException(
                        "Post Not Found By ID:"+id
                )
        ),PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO toUpdate ,Long id) {
        PostEntity oldPostEntity =postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post Not Found"));
        toUpdate.setId(id);
        modelMapper.map(toUpdate,oldPostEntity);
        PostEntity savedPostEntity =postRepository.save(oldPostEntity);
        return modelMapper.map(savedPostEntity, PostDTO.class);

    }

    @Override
    public PostDTO createNewPost(PostDTO toCreatePostDto) {

        PostEntity createPostEntity =modelMapper.map(toCreatePostDto,PostEntity.class);
        PostEntity savedPostEntity =postRepository.save(createPostEntity);
        return modelMapper.map(savedPostEntity,PostDTO.class);
    }
}


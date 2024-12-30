package com.prod.springboot_production_ready.services.implementations;

import com.prod.springboot_production_ready.dto.PostDto;
import com.prod.springboot_production_ready.entities.PostEntity;
import com.prod.springboot_production_ready.excpetions.ResourceNotFoundException;
import com.prod.springboot_production_ready.repositories.PostRepository;
import com.prod.springboot_production_ready.services.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDto> getAllPosts() {

        return postRepository.findAll().stream().map(postEntity -> modelMapper.map(postEntity,PostDto.class)).toList();

    }

    @Override
    public PostDto getPostById(Long id) {
        return modelMapper.map( postRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException(
                        "Post Not Found By ID:"+id
                )
        ),PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto toUpdate ,Long id) {
        PostEntity oldPostEntity =postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post Not Found"));
        toUpdate.setId(id);
        modelMapper.map(toUpdate,oldPostEntity);
        PostEntity savedPostEntity =postRepository.save(oldPostEntity);
        return modelMapper.map(savedPostEntity, PostDto.class);

    }

    @Override
    public PostDto createNewPost(PostDto toCreatePostDto) {

        PostEntity createPostEntity =modelMapper.map(toCreatePostDto,PostEntity.class);
        PostEntity savedPostEntity =postRepository.save(createPostEntity);
        return modelMapper.map(savedPostEntity,PostDto.class);
    }
}

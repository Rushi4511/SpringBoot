package com.securityApp.SpringSecurity.services;



import com.securityApp.SpringSecurity.dto.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long postId);

    PostDTO updatePost(PostDTO toUpdate ,Long id);

}

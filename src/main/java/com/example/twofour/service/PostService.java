package com.example.twofour.service;

import com.example.twofour.dto.PostRequestDto;
import com.example.twofour.dto.PostResponseDto;
import com.example.twofour.entity.Post;
import com.example.twofour.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public PostResponseDto save(PostRequestDto dto) {
        Post post = new Post(dto.getTitle());
        postRepository.save(post);
        return new PostResponseDto(post.getId(),post.getTitle());

    }

    @Transactional
    public PostResponseDto update(Long postId, PostRequestDto dto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalStateException("post 찾을 수 없음")
        );
        post.update(dto.getTitle());
        return new PostResponseDto(post.getId(), post.getTitle());
    }

    public List<PostResponseDto> findAll() {
        List<Post> posts = postRepository.findAll();

        List<PostResponseDto> dtos = new ArrayList<>();
        for (Post post : posts) {
            dtos.add(new PostResponseDto(post.getId(),post.getTitle()));
        }
        return dtos;
    }
}

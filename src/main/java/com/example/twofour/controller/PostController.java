package com.example.twofour.controller;

import com.example.twofour.dto.PostRequestDto;
import com.example.twofour.dto.PostResponseDto;
import com.example.twofour.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public PostResponseDto save(@RequestBody PostRequestDto dto) {
        return postService.save(dto);
    }

    @PutMapping("/posts/{postId}")
    public PostResponseDto update(
            @PathVariable Long postId,
            @RequestBody PostRequestDto dto
    ) {
        return postService.update(postId, dto);
    }

    @GetMapping("/posts")
    public List<PostResponseDto> getAll() {
        return postService.findAll();
    }
}

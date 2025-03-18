package com.example.twofour.service;

import com.example.twofour.dto.CommentRequestDto;
import com.example.twofour.dto.CommentResponseDto;
import com.example.twofour.entity.Comment;
import com.example.twofour.entity.Post;
import com.example.twofour.repository.CommentRepository;
import com.example.twofour.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void save(CommentRequestDto dto) {

        Post post = postRepository.findById(dto.getPostId()).orElseThrow(
                () -> new IllegalStateException("게시글이 존재하지 않습니다.")
        );
        Comment comment = new Comment(dto.getContent(), post);
        commentRepository.save(comment);

    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> findAll() {
        List<Comment> comments = commentRepository.findAll();

        List<CommentResponseDto> dtos = new ArrayList<>();
        for (Comment comment : comments) {
            dtos.add(new CommentResponseDto(comment.getId(), comment.getContent()));
        }
        return dtos;
    }

    @Transactional
    public void update(Long commentId, CommentRequestDto dto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalStateException("해당 댓글이 존재하지않습니다.")
        );
        comment.update(dto.getContent());

    }
}

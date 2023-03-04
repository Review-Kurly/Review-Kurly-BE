package sparat.spartaclone.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparat.spartaclone.comment.dto.CommentRequestDto;
import sparat.spartaclone.comment.dto.CommentResponseDto;
import sparat.spartaclone.comment.repository.CommentRepository;
import sparat.spartaclone.common.entity.Comment;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    
    @Transactional
    public CommentResponseDto createComment(CommentRequestDto requestDto) {
        Comment comment = commentRepository.save(new Comment(requestDto, null, null));
        return CommentResponseDto.of(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 댓글입니다.")
        );

        //user 검증 추가

        comment.updateComment(commentId, requestDto.getContent());
        return CommentResponseDto.of(comment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
    }
}

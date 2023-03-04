package sparat.spartaclone.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparat.spartaclone.comment.dto.CommentRequestDto;
import sparat.spartaclone.comment.dto.CommentResponseDto;
import sparat.spartaclone.comment.repository.CommentRepository;
import sparat.spartaclone.common.CustomClientException;
import sparat.spartaclone.common.entity.Comment;
import sparat.spartaclone.common.entity.User;
import sparat.spartaclone.common.enums.ErrorMessage;
import sparat.spartaclone.common.handler.GlobalExceptionHandler;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    
    @Transactional
    public CommentResponseDto createComment(CommentRequestDto requestDto, User user) {
        Comment comment = commentRepository.save(new Comment(requestDto, null, null));
        return CommentResponseDto.of(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new CustomClientException("존재하지 않는 댓글입니다.")
        );

        if(!user.getId().equals(comment.getUser().getId())) {
            throw new CustomClientException("ID가 일치하지 않습니다.");
        }

        comment.updateComment(commentId, requestDto.getContent());
        return CommentResponseDto.of(comment);
    }

    @Transactional
    public void deleteComment(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new EntityNotFoundException(ErrorMessage.COMMENT_NOT_FOUND.getMessage())
        );

        if(!user.getId().equals(comment.getUser().getId())) {
            throw new EntityNotFoundException(ErrorMessage.ACCESS_DENIED.getMessage());
        }
        commentRepository.deleteById(commentId);
    }
}

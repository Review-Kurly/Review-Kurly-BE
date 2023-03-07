package sparat.spartaclone.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparat.spartaclone.comment.dto.CommentRequestDto;
import sparat.spartaclone.comment.dto.CommentResponseDto;
import sparat.spartaclone.comment.repository.CommentLikeRepository;
import sparat.spartaclone.comment.repository.CommentRepository;
import sparat.spartaclone.common.CustomClientException;
import sparat.spartaclone.common.entity.Comment;
import sparat.spartaclone.common.entity.CommentLike;
import sparat.spartaclone.common.entity.Review;
import sparat.spartaclone.common.entity.User;
import sparat.spartaclone.common.enums.ErrorMessage;
import sparat.spartaclone.common.handler.GlobalExceptionHandler;
import sparat.spartaclone.review.repository.ReviewRepository;
import sparat.spartaclone.user.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final CommentLikeRepository commentLikeRepository;

    @Transactional
    public List<CommentResponseDto> getCommentList(Long reviewId, String username) {
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();

        Optional<User> user = userRepository.findByUsername(username);

        Set<Comment> myLikedCommentSet = new HashSet<Comment>();
        if (user.isPresent()) {
            List<CommentLike> commentLikeList = commentLikeRepository.findByUserIdAndReviewId(user.get().getId(), reviewId);
            for (CommentLike commentLike : commentLikeList) {
                myLikedCommentSet.add(commentLike.getComment());
                System.out.println(commentLike.getComment().getId());
            }
        }

        List<Comment> commentList = commentRepository.findAllByReviewIdOrderByCreatedAtAsc(reviewId);
        for (Comment comment : commentList) {
            commentResponseDtoList.add(new CommentResponseDto(comment, myLikedCommentSet.contains(comment)));
        }

        return commentResponseDtoList;
    }

    @Transactional
    public CommentResponseDto createComment(Long reviewId, CommentRequestDto requestDto, String username) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new EntityNotFoundException(ErrorMessage.REVIEW_NOT_FOUND.getMessage())
        );

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException(ErrorMessage.WRONG_USERNAME.getMessage())
        );

        Comment comment = commentRepository.save(new Comment(requestDto, review, user));
        return new CommentResponseDto(comment, false);
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto, String username) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new EntityNotFoundException(ErrorMessage.COMMENT_NOT_FOUND.getMessage())
        );

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException(ErrorMessage.WRONG_USERNAME.getMessage())
        );

        Optional<CommentLike> commentLike = commentLikeRepository.findByUserIdAndCommentId(user.getId(), commentId);

        comment.updateComment(commentId, requestDto.getCommentTitle(), requestDto.getContent());
        return new CommentResponseDto(comment, commentLike.isPresent());
    }

    @Transactional
    public void deleteComment(Long commentId, String username) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new EntityNotFoundException(ErrorMessage.COMMENT_NOT_FOUND.getMessage())
        );

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException(ErrorMessage.WRONG_USERNAME.getMessage())
        );

        commentRepository.deleteById(commentId);
    }

    @Transactional
    public CommentResponseDto toggleLikes(Long reviewId, Long commentId, String username) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new EntityNotFoundException(ErrorMessage.REVIEW_NOT_FOUND.getMessage())
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new EntityNotFoundException(ErrorMessage.COMMENT_NOT_FOUND.getMessage())
        );

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new EntityNotFoundException(ErrorMessage.WRONG_USERNAME.getMessage())
        );

        Optional<CommentLike> commentLike = commentLikeRepository.findByUserIdAndCommentId(user.getId(), comment.getId());

        if(commentLike.isEmpty()) {
            commentLikeRepository.save(new CommentLike(comment, user, review));
        } else {
            commentLikeRepository.deleteByUserIdAndCommentId(user.getId(), comment.getId());
        }
        return new CommentResponseDto(comment, !commentLike.isPresent());
    }
}
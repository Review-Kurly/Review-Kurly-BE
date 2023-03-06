package sparat.spartaclone.comment.controller;

import com.google.protobuf.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sparat.spartaclone.comment.dto.CommentRequestDto;
import sparat.spartaclone.comment.dto.CommentResponseDto;
import sparat.spartaclone.comment.service.CommentService;
import sparat.spartaclone.common.ApiResponse;
import sparat.spartaclone.common.constant.ConstantTable;
import sparat.spartaclone.common.entity.Comment;
import sparat.spartaclone.common.security.UserDetailsImpl;

import java.util.List;

@Tag(name = "comment")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/")
    @Operation(summary = "댓글 리스트", description = "댓글 리스트")
    public ApiResponse<List<CommentResponseDto>> getCommentList(@RequestParam Long reviewId) {
        return ApiResponse.successOf(HttpStatus.OK, commentService.getCommentList(reviewId));
    }

    @PostMapping("/{reviewId}")
    @Operation(summary = "댓글 등록", description = "댓글 등록, " + ConstantTable.HEADER_NEEDED)
    public ApiResponse<CommentResponseDto> createComment(
            @PathVariable Long reviewId,
            @RequestBody CommentRequestDto requestDto,
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return ApiResponse.successOf(HttpStatus.CREATED, commentService.createComment(reviewId, requestDto, userDetails.getUser().getUsername()));
    }

    @PutMapping("/{commentId}")
    @Operation(summary = "댓글 수정", description = "댓글 수정, " + ConstantTable.HEADER_NEEDED)
    public ApiResponse<CommentResponseDto> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto requestDto,
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ApiResponse.successOf(HttpStatus.OK, commentService.updateComment(commentId, requestDto, userDetails.getUser().getUsername()));
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "댓글 삭제", description = "댓글 삭제, " + ConstantTable.HEADER_NEEDED)
    public ApiResponse<CommentResponseDto> deleteComment(
            @PathVariable Long commentId,
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentService.deleteComment(commentId, userDetails.getUser().getUsername());
        return ApiResponse.successOf(HttpStatus.OK, null);
    }

    @PostMapping("/likes/{commentId}")
    @Operation(summary = "댓글 좋아요", description = "댓글 좋아요")
    public ApiResponse<CommentResponseDto> toggleLikes(@PathVariable Long commentId,
                                                       @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ApiResponse.successOf(HttpStatus.OK, commentService.toggleLikes(commentId, userDetails.getUser().getUsername()));
    }
}

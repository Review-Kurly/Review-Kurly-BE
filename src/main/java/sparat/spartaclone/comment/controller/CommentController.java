package sparat.spartaclone.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sparat.spartaclone.comment.dto.CommentRequestDto;
import sparat.spartaclone.comment.dto.CommentResponseDto;
import sparat.spartaclone.common.ApiResponse;

@Tag(name = "comment")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    @PostMapping("/")
    @Operation(summary = "댓글 등록", description = "댓글 등록")
    public ApiResponse<CommentResponseDto> createComment(@RequestBody CommentRequestDto requestDto) {
        return ApiResponse.successOf(HttpStatus.CREATED, null);
    }

    @PutMapping("/{commentId}")
    @Operation(summary = "댓글 수정", description = "댓글 수정")
    public ApiResponse<CommentResponseDto> updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto) {
        return ApiResponse.successOf(HttpStatus.OK, null);
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "댓글 삭제", description = "댓글 삭제")
    public ApiResponse<CommentResponseDto> deleteComment(@PathVariable Long commentId) {
        return ApiResponse.successOf(HttpStatus.OK, null);
    }
}

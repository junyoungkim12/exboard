package hellojpa.exboard.controller;

import hellojpa.exboard.request.BoardCreateRequest;
import hellojpa.exboard.request.CommentCreateRequest;
import hellojpa.exboard.request.CommentDeleteRequest;
import hellojpa.exboard.request.CommentEditRequest;
import hellojpa.exboard.response.BoardResponse;
import hellojpa.exboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment")
    public BoardResponse createComment(
            @RequestBody CommentCreateRequest commentCreateRequest
            ){
        return commentService.createComment(commentCreateRequest.getBoardId(),
                commentCreateRequest.getCommentContent());
    }

    @PutMapping("/comment")
    public String editComment(
            @RequestBody CommentEditRequest commentEditRequest
            ){
        commentService.editComment(commentEditRequest.getBoardId(), commentEditRequest.getCommentId(), commentEditRequest.getCommentContent());
        return "OK";
    }

    @DeleteMapping("/comment")
    public String deleteComment(
            @RequestBody CommentDeleteRequest commentDeleteRequest
            ){
        commentService.deleteComment(commentDeleteRequest.getBoardId(), commentDeleteRequest.getCommentId());
        return "OK";
    }
}

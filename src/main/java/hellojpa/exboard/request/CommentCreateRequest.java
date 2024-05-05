package hellojpa.exboard.request;

import lombok.Data;

@Data
public class CommentCreateRequest {
    private Long boardId;
    private String commentContent;
}

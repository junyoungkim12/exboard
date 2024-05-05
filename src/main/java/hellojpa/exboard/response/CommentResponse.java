package hellojpa.exboard.response;

import hellojpa.exboard.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentResponse {
  private Long commentId;
  private String content;

  static public CommentResponse from(Comment comment) {
    return new CommentResponse(comment.getCommentId(), comment.getContent());
  }
}

package hellojpa.exboard.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentEditRequest {
  private Long boardId;
  private Long commentId;
  private String commentContent;
}

package hellojpa.exboard.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDeleteRequest {
  private Long boardId;
  private Long commentId;
}

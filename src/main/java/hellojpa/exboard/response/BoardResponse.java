package hellojpa.exboard.response;

import hellojpa.exboard.entity.Board;
import hellojpa.exboard.entity.BoardStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
public class BoardResponse {
    private Long boardId;
    private String title;
    private String content;
    private BoardStatus boardStatus;
    private List<CommentResponse> commentResponseList;

    static public BoardResponse from (Board board){
        return new BoardResponse(
                board.getBoardId(),
                board.getTitle(),
                board.getContent(),
                board.getBoardStatus(),
                board.getComments().stream()
                        .map(CommentResponse::from)
                        .toList());
    }
}

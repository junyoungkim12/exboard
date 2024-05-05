package hellojpa.exboard.request;

import lombok.Data;

@Data
public class BoardEditRequest {
    private Long boardId;
    private String content;
}

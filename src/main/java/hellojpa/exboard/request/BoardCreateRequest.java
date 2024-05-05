package hellojpa.exboard.request;

import lombok.Data;

@Data
public class BoardCreateRequest {
    private String title;
    private String content;

}

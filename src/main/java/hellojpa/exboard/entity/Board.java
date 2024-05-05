package hellojpa.exboard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Where(clause = "board_status='ACTIVE'")
@SQLDelete(sql = "UPDATE board SET board_status='DELETE' WHERE board_id=?")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private BoardStatus boardStatus;

    @OneToMany(mappedBy = "board", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Comment> comments = new ArrayList<>();

    public Board addComment(String commentContent) {
        Comment comment = new Comment();
        comment.setContent(commentContent);
        comment.setBoard(this);
        comment.setCommentStatus(CommentStatus.ACTIVE);
        this.comments.add(comment);
        return this;
    }
}

package hellojpa.exboard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Where(clause = "comment_status='ACTIVE'")
@SQLDelete(sql = "UPDATE comment SET comment_status='DELETE' where comment_id=?")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String content;

    @Enumerated(EnumType.STRING)
    private CommentStatus commentStatus;


    @ManyToOne
    private Board board;
}


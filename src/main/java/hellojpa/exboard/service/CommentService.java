package hellojpa.exboard.service;

import hellojpa.exboard.entity.Board;
import hellojpa.exboard.entity.Comment;
import hellojpa.exboard.repository.BoardRepository;
import hellojpa.exboard.repository.CommentRepository;
import hellojpa.exboard.response.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public BoardResponse createComment(Long boardId, String commentContent) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            board.addComment(commentContent);
            Board savedBoard = boardRepository.save(board);
            return BoardResponse.from(savedBoard);
        } else {
            throw new RuntimeException("존재하지 않는 게시글입니다.");
        }
    }

    public void editComment(Long boardId, Long commentId, String commentContent) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        Optional<Comment> optionalComment = commentRepository.findById(commentId);

        if (optionalBoard.isPresent() && optionalComment.isPresent()) {
            Board board = optionalBoard.get();
            Comment comment = optionalComment.get();

            if (!board.equals(comment.getBoard())) {
                throw new IllegalArgumentException("게시글에 존재하지 않는 댓글입니다.");
            }

            comment.setContent(commentContent);
            commentRepository.save(comment);
        } else {
            throw new RuntimeException("존재하지 않는 게시글이나 댓글입니다.");
        }
    }

    public void deleteComment(Long boardId, Long commentId) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        Optional<Comment> optionalComment = commentRepository.findById(commentId);

        if (optionalBoard.isPresent() && optionalComment.isPresent()) {
            Board board = optionalBoard.get();
            Comment comment = optionalComment.get();

            if (!board.equals(comment.getBoard())) {
                throw new IllegalArgumentException("게시글에 존재하지 않는 댓글입니다.");
            }

            commentRepository.delete(comment);
        } else {
            throw new RuntimeException("존재하지 않는 게시글이나 댓글입니다.");
        }
    }
}

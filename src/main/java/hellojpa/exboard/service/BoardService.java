package hellojpa.exboard.service;

import hellojpa.exboard.entity.Board;
import hellojpa.exboard.entity.BoardStatus;
import hellojpa.exboard.repository.BoardRepository;
import hellojpa.exboard.response.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardResponse> searchBoardList(int page, int pageSize, Sort.Direction direction){
       return boardRepository.findAll(
                PageRequest.of(page, pageSize, Sort.by(direction, "boardId"))
        ).map(BoardResponse::from).toList();
    }


    public BoardResponse searchBoard(Long boardId){
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        if (optionalBoard.isPresent()) { // boardId가 존재하면 게시글 조회
            Board board = optionalBoard.get();
            return BoardResponse.from(board);
        } else {
            throw new RuntimeException("존재하지 않는 게시글입니다.");
        }
    }

    public BoardResponse createBoard(String title, String content){
        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        board.setBoardStatus(BoardStatus.ACTIVE);
        return BoardResponse.from(boardRepository.save(board));
    }


    public BoardResponse editBoard(Long boardId, String content) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);

        if (optionalBoard.isPresent()) { // boardId가 존재하면 게시글 수정
            Board board = optionalBoard.get();
            board.setContent(content);
            return BoardResponse.from(board);
        } else {
            throw new RuntimeException("존재하지 않는 게시글입니다.");
        }
    }

    public Long deleteBoard(Long boardId) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);

        if (optionalBoard.isPresent()) { // boardId가 존재하면 게시글 삭제
            Board board = optionalBoard.get();
            boardRepository.delete(board);
            return board.getBoardId();
        } else {
            throw new RuntimeException("존재하지 않는 게시글입니다.");
        }

    }
    }
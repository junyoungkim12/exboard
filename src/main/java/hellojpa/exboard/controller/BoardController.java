package hellojpa.exboard.controller;

import hellojpa.exboard.request.BoardCreateRequest;
import hellojpa.exboard.request.BoardDeleteRequest;
import hellojpa.exboard.request.BoardEditRequest;
import hellojpa.exboard.response.BoardResponse;
import hellojpa.exboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    @Autowired
    private final BoardService boardService;

    @GetMapping("/board")
    public BoardResponse searchBoard(
            @RequestParam("boardId") Long boardId
    ){
        return boardService.searchBoard(boardId);
    }

    @GetMapping("/boardList")
    public List<BoardResponse> searchBoardList(
            @RequestParam("page") int page,
            @RequestParam("pageSize") int pageSize,
            @RequestParam("direction") Sort.Direction direction
    ){
        return boardService.searchBoardList(page, pageSize, direction);
    }

    @PostMapping("/board")
    public BoardResponse createBoard(
            //BoardCreateRequest를 통해 게시글 생성에 필요한 title과 content를 요청
            @RequestBody BoardCreateRequest boardCreateRequest
            ){
        //boardService 호출
       return boardService.createBoard(boardCreateRequest.getTitle(), boardCreateRequest.getContent());

    }

    @PutMapping("/board")
    public BoardResponse editBoard(
            @RequestBody BoardEditRequest boardEditRequest
            ){
        return boardService.editBoard(boardEditRequest.getBoardId(), boardEditRequest.getContent());
    }

    @DeleteMapping("/board")
    public Long deleteBoard(
            @RequestBody BoardDeleteRequest boardDeleteRequest
            ){
        return boardService.deleteBoard(boardDeleteRequest.getBoardId());
    }
}

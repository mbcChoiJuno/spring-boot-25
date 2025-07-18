package org.mbc.board.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.mbc.board.models.board.dto.BoardDTO;
import org.mbc.board.models.board.dto.PageRequestDTO;
import org.mbc.board.models.board.service.IBoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Autowired
    IBoardServiceImpl boardService;

    @Test
    public void testPerformPostBoard() {
       var board = BoardDTO.builder()
               .title("등록 테스트 제목")
               .content("등록 테스트 내용")
               .writer("junotest")
               .build();

       var boardIndex = boardService.performPostBoard(board);

       log.info(boardIndex);
    }

    @Test
    public void testGetBoardList() {
        int page = 0;
        int pageChildCount = 10;

        var boards = boardService.getBoardList(page, pageChildCount);

        for (BoardDTO board : boards) {
            log.info(board.toString());
        }
    }

    @Test
    public void testGetBoardPage() {

        var pageable = PageRequestDTO.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();

        var boardPage = boardService.getBoardPage(pageable);

        for (var board : boardPage.getItems()) {
            log.info(board.toString());
        }
    }

    @Test
    public void testGetBoardDetail() {
        Long boardIndex = 1L;
        var board = boardService.getBoardDetail(boardIndex);

        log.info(board.toString());
    }

    @Test
    public void testPerformModifyBoard() {
        var board = BoardDTO.builder()
                .boardIndex(4L)
                .title("수정 테스트 제목")
                .content("수정 테스트 내용")
                .writer("junotest")
                .build();

        var modifyBoardIndex = boardService.performModifyBoard(board);

        log.info(modifyBoardIndex);
    }

    @Test
    public void testPerformDeleteBoard() {
        Long boardIndex = 201L;

        var deleteBoardIndex = boardService.performSoftDeleteBoard(boardIndex);

        log.info(deleteBoardIndex);
    }
}

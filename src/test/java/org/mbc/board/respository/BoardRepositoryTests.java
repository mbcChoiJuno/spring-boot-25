package org.mbc.board.respository;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.mbc.board.models.board.domain.Board;
import org.mbc.board.models.board.repository.IBoardJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.util.NoSuchElementException;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private IBoardJpaRepository boardJpaRepository;

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            String title = "제목%d".formatted(i);
            String content = "내용%d".formatted(i);
            String writer = "user%d".formatted(i % 10);

            var board = Board.builder()
                    .title(title)
                    .content(content)
                    .writer(writer)
                    .build();

            var result = boardJpaRepository.save(board);

            log.info("%d. %s | %s .... %s".formatted(result.getBoardIndex(),
                    result.getTitle(),
                    result.getContent(),
                    result.getWriter()));
        });
    }

    @Test
    public void testSelectAll() {
        var boards = boardJpaRepository.findAll();

        boards.forEach(board -> {
            log.info("%d. %s | %s .... %s".formatted(board.getBoardIndex(),
                    board.getTitle(),
                    board.getContent(),
                    board.getWriter()));
        });
    }

    @Test
    public void testSelectOne() {
        var id = 201L;
        var board = boardJpaRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NoSuchElementException();
                });

        log.info("%d. %s | %s .... %s".formatted(board.getBoardIndex(),
                board.getTitle(),
                board.getContent(),
                board.getWriter()));
    }

    @Test
    @Transactional
    @Commit
    public void testUpdate() {
        var id = 2L;

        var board = boardJpaRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NoSuchElementException(); //new RuntimeException("해당 게시글이 없습니다.");
                });

        String title = "수정1제목%d".formatted(id);
        String content = "수정1내용%d".formatted(id);
        board.setTitle(title);
        board.setContent(content);
    }

    @Test
    public void testDelete() {
        var id = 3L;
        boardJpaRepository.deleteById(id);
    }


    @Test
    public void testPaging() {

        int pageIndex = 21;
        int pageItemCount = 10;

        Pageable pageable = PageRequest.of(pageIndex, pageItemCount,
                Sort.by("boardIndex").descending());

        var pageSet = boardJpaRepository.findAll(pageable);

        if (pageIndex > pageSet.getTotalPages()) {
            // TODO: exception
            return;
        }


        Long totalElementCnt = pageSet.getTotalElements(); // 전체 게시물
        int totalPageCnt = pageSet.getTotalPages();        // 전체 페이지수
        var pageNum = pageSet.getNumber();                 // 현재 페이지
        var pageSize = pageSet.getSize();                  // 페이지당 item 개수

        var pageHasPrevious = pageSet.hasPrevious();       // 이전 페이지 존재 여부
        var pageHasNext = pageSet.hasNext();               // 다음 페이지 존재 여부
        var pageIsFirst = pageSet.isFirst();               // 첫 페이지 여부
        var pageIsLast = pageSet.isLast();                 // 마지막 페이지 여부

        log.info("totalElementCnt:" + totalElementCnt);
        log.info("totalPageCnt:" + totalPageCnt);
        log.info("pageNum:" + pageNum);
        log.info("pageSize:" + pageSize);

        log.info("pageHasPrevious:" + pageHasPrevious);
        log.info("pageHasNext:" + pageHasNext);
        log.info("pageIsFirst:" + pageIsFirst);
        log.info("pageIsLast:" + pageIsLast);

        pageSet.forEach(board -> {
            log.info("%d. %s | %s .... %s".formatted(board.getBoardIndex(),
                    board.getTitle(),
                    board.getContent(),
                    board.getWriter()));
        });
    }
}

package org.mbc.board.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.mbc.board.domain.Board;
import org.mbc.board.domain.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest // 메서드용 테스트 동작
@Log4j2 // 로그용
public class ReplyRepositoryTests {
    // 영속성 계층에 테스트용

    @Autowired // 생성자 자동 주입
    private ReplyRepository replyRepository;

    @Test
    public void testInserts() {

        var bno = 100L;
        // 데이터베이스에 데이터 주입(c) 테스트 코드
        IntStream.rangeClosed(1, 20).forEach(i -> {

            var board = Board.builder()
                    .bno(bno)
                    .build();

            var reply = Reply.builder()
                    .replyText("repo tests")
                    .replyer("tester")
                    .board(board)
                    .build();

            replyRepository.save(reply);

                }// forEach문 종료
        );// IntStream. 종료

    } // testInsert 메서드 종료
    @Test
    public void testInsert(){

        var bno = 100L;

        var board = Board.builder()
                .bno(bno)
                .build();

        var reply = Reply.builder()
                .replyText("repo tests")
                .replyer("tester")
                .board(board)
                .build();

        replyRepository.save(reply);
    }

    @Test
    public void testBoardReplies() {
        var bno = 100L;

        Pageable pageable = PageRequest.of(0, 10, Sort.by("rno"));

        Page<Reply> result = replyRepository.listOfBoard(bno, pageable);

        result.forEach(reply -> {
            log.info(reply);
        });
    }


} // 클래스 종료

package org.mbc.board.models.board.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.mbc.board.models.board.domain.Board;
import org.mbc.board.models.board.domain.QBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

// DSL ? JPQL ?
public class IBoardSearchImpl extends QuerydslRepositorySupport implements IBoardSearch {

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     */
    public IBoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {

        var board = QBoard.board;

        JPQLQuery<Board> query = from(board);           // SELECT * FROM board_tbl

        BooleanBuilder booleanBuilder = new BooleanBuilder(); // 연산에 우선순위 괄호() 처리해줌
//        query.where(board.title.contains("1"));         // WHERE board_title LIKE 1
        booleanBuilder.or(board.title.contains("11"));      // WHERE board_title LIKE 11
        booleanBuilder.or(board.content.contains("11"));    // WHERE board_content LIKE 11
        // WHERE board_title LIKE 11 or board_content LIKE 11

        query.where(booleanBuilder);                // (WHERE board_title LIKE 11 or board_content LIKE 11)
        query.where(board.boardIndex.gt(0L));       // WHERE board_index > 0
        // WHERE (board_title LIKE 11 or board_content LIKE 11) AND board_index > 0


        this.getQuerydsl().applyPagination(pageable, query);

        List<Board> boards = query.fetch();             // 쿼리 실행

        long fetchCount = query.fetchCount();

        return null;
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {

        var board = QBoard.board;
        var query = from(board);

        // 필터타입 o & 검색어 o
        if ( (types != null && types.length > 0) && (keyword != null)) {
            var booleanBuilder = new BooleanBuilder();
            for (String type : types) {

                switch (type) {
                    case "t" -> { // title 필터
                        booleanBuilder.or(board.title.contains(keyword));
                    }
                    case "c" -> { // content 필터
                        booleanBuilder.or(board.content.contains(keyword));
                    }
                    case "w" -> { // writer 필터
                        booleanBuilder.or(board.writer.contains(keyword));
                    }
                }
            }
            query.where(booleanBuilder);
        }

        query.where(board.boardIndex.gt(0L));

        this.getQuerydsl().applyPagination(pageable, query);

        var boards = query.fetch();

        Long count = query.fetchCount();

        return new PageImpl<>(boards, pageable, count);
    }
}




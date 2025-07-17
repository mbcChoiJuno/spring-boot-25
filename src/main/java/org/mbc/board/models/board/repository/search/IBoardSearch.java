package org.mbc.board.models.board.repository.search;

import org.mbc.board.models.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBoardSearch {

    Page<Board> search1(Pageable pageable);

    // 요구기능
    // 제목, 내용, 작성자 로 검색
    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);
}

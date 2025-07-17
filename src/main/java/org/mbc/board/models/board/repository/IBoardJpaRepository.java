package org.mbc.board.models.board.repository;

import org.mbc.board.models.board.domain.Board;
import org.mbc.board.models.board.repository.search.IBoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBoardJpaRepository extends JpaRepository<Board, Long>, IBoardSearch {

    // Jpa Query Method 작성하면 sql query로 변환되어 작동
    // https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    Page<Board> findByTitleContainingOrderByBoardIndexDesc(String keyword, Pageable pageable);
    // findByTitle: select where board_title = ?
    // OrderByBoardIndex : order by board_index DESC
    // 단점 -> 메서드명 가독성 없음 (잘 사용 안한다고 함)

    // @Query JPQL
    @Query("select b from Board b where b.title like concat('%', :keyword, '%')")
    Page<Board> findByKeyword(String keyword, Pageable pageable);
    // select * from board where title like '%keyword%'
    // 단점 -> join 등 복잡한 쿼리 사용 못함
    //          원하는 속성만 추출해서 객체 배열 처리하거나 DTO로 처리 불가
    //          native query 속성 값을 true로 지정해서 특정 DB에서 동작하는 sql 사용 안됨

    // Query dsl?
    // 네이티브 쿼리 : 진짜 쿼리문 사용하기
    @Query(value = "select now()", nativeQuery = true)
    String getTime();

}

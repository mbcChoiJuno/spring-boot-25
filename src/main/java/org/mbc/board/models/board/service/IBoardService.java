package org.mbc.board.models.board.service;

import org.mbc.board.models.board.dto.BoardDTO;
import org.mbc.board.models.board.dto.PageRequestDTO;
import org.mbc.board.models.board.dto.PageResponseDTO;

import java.util.List;

public interface IBoardService {

    /**
     * 게시물 등록
     * @param boardDTO
     * @return
     */
    Long performPostBoard(BoardDTO boardDTO);

    BoardDTO getBoardDetail(Long boardIndex);

    List<BoardDTO> getBoardList(int page, int childCount);

    Long performModifyBoard(BoardDTO boardDTO);

    Long performSoftDeleteBoard(Long boardIndex);

    PageResponseDTO<BoardDTO> getBoardPage(PageRequestDTO request);
}

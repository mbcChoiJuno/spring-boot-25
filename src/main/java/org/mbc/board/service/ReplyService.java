package org.mbc.board.service;

import org.mbc.board.dto.PageRequestDTO;
import org.mbc.board.dto.PageResponseDTO;
import org.mbc.board.dto.ReplyDTO;

public interface ReplyService {

	Long register(ReplyDTO replyDTO);

	ReplyDTO read(Long id);

	PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);

	void modify(ReplyDTO replyDTO);

	void remove(Long rno);
}

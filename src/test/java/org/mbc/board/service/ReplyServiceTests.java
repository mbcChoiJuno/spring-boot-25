package org.mbc.board.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.mbc.board.domain.Reply;
import org.mbc.board.dto.PageRequestDTO;
import org.mbc.board.dto.ReplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
@Log4j2
public class ReplyServiceTests {

	@Autowired
	private ReplyService replyService;

	@Test
	public void testRegister() {

		var replyDTO = ReplyDTO.builder()
				.replyText("서비스 댓글등록 테스트")
				.replyer("서비스 테스트")
				.bno(98L)
				.build();

		log.info(replyService.register(replyDTO));
	}

	@Test
	public void test() {

		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
		replyService.getListOfBoard(1L, pageRequestDTO);
	}

}

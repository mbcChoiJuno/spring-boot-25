package org.mbc.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mbc.board.domain.Board;
import org.mbc.board.domain.Reply;
import org.mbc.board.dto.PageRequestDTO;
import org.mbc.board.dto.PageResponseDTO;
import org.mbc.board.dto.ReplyDTO;
import org.mbc.board.repository.BoardRepository;
import org.mbc.board.repository.ReplyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService {

//	private final BoardRepository boardRepository; // 추가

	private final ReplyRepository replyRepository;
	private final ModelMapper modelMapper;

	@Override
	public Long register(ReplyDTO replyDTO) {
		Reply reply = modelMapper.map(replyDTO, Reply.class);

		/*// 추가
		Board board = boardRepository.findById(replyDTO.getBno())
				.orElseThrow(() -> new DataIntegrityViolationException("해당 게시물이 존재하지 않습니다: " + replyDTO.getBno()));

		// 추가
		reply.setBoard(board);*/

		log.info(reply);

		Long rno = replyRepository.save(reply).getRno();

		return rno;
	}

	@Override
	public ReplyDTO read(Long rno) {
		var replyOptional = replyRepository.findById(rno);
		var reply = replyOptional.orElseThrow();

		return modelMapper.map(reply, ReplyDTO.class);
	}

	@Override
	public PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO) {

		Pageable pageable = PageRequest.of(
				pageRequestDTO.getPage() <= 0 ? 0 : pageRequestDTO.getPage() -1,

				pageRequestDTO.getSize(),
				Sort.by("rno").ascending()
		);

		Page<Reply> result = replyRepository.listOfBoard(bno, pageable);

		List<ReplyDTO> dtoList = result.getContent().stream()
				.map(reply -> modelMapper.map(reply, ReplyDTO.class))
				.collect(Collectors.toList());





		return PageResponseDTO.<ReplyDTO>withAll()
				.pageRequestDTO(pageRequestDTO)
				.dtoList(dtoList)
				.total((int) result.getTotalElements())
				.build();
	}

	@Override
	@Transactional
	public void modify(ReplyDTO replyDTO) {
		var replyOptional = replyRepository.findById(replyDTO.getRno());
		var reply = replyOptional.orElseThrow();

		reply.setReplyText(replyDTO.getReplyText());
	}

	@Override
	public void remove(Long rno) {
		replyRepository.deleteById(rno);
	}
}

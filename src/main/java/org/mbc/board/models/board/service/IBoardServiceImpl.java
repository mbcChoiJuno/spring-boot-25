package org.mbc.board.models.board.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mbc.board.models.board.domain.Board;
import org.mbc.board.models.board.dto.BoardDTO;
import org.mbc.board.models.board.dto.PageRequestDTO;
import org.mbc.board.models.board.dto.PageResponseDTO;
import org.mbc.board.models.board.repository.IBoardJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class IBoardServiceImpl implements IBoardService {

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final IBoardJpaRepository boardJpaRepository;

    @Override
    public Long performPostBoard(BoardDTO boardDTO) {
        var board = modelMapper.map(boardDTO, Board.class);
        return boardJpaRepository.save(board).getBoardIndex();
    }

    @Override
    public BoardDTO getBoardDetail(Long boardIndex) {
        var board = boardJpaRepository.findById(boardIndex)
                .orElseThrow();
        return modelMapper.map(board, BoardDTO.class);
    }

    @Override
    public List<BoardDTO> getBoardList(int page, int childCount) {

        List<BoardDTO> boardList = new ArrayList<BoardDTO>();

        int pageIndex = 0;
        int pageChildCount = 10;

        Pageable pageable = PageRequest.of(pageIndex, (pageIndex + 1) * pageChildCount, Sort.by("boardIndex").descending());

        var result = boardJpaRepository.findAll(pageable);

        result.forEach(board -> {
            boardList.add(modelMapper.map(board, BoardDTO.class));
        });

        return boardList;
    }

    @Override
    public Long performModifyBoard(BoardDTO boardDTO) {
        var board = boardJpaRepository.findById(boardDTO.getBoardIndex())
                .orElseThrow();

        board.setContent(boardDTO.getContent());
        board.setTitle(boardDTO.getTitle());

        return board.getBoardIndex();
    }

    @Override
    public Long performSoftDeleteBoard(Long boardIndex) {
        var board = boardJpaRepository.findById(boardIndex)
                .orElseThrow();

        board.setDeletedYn("Y");

        return board.getBoardIndex();
    }

    @Override
    public PageResponseDTO<BoardDTO> getBoardPage(PageRequestDTO request) {

        String order = "boardIndex";
        var pageable = request.getPageable(order);

        var types = request.getTypes();
        var keyword = request.getKeyword();

        var result = boardJpaRepository.searchAll(types, keyword, pageable);

        var items = result.getContent().stream()
                .map(board -> modelMapper.map(board, BoardDTO.class))
                .collect(Collectors.toList());

        // vs
        /*
        result.forEach(board -> {
            modelMapper.map(board, BoardDTO.class);
        });*/

        return PageResponseDTO.<BoardDTO>withAll()
                .request(request)
                .items(items)
                .total((int) result.getTotalElements())
                .build();
    }
}

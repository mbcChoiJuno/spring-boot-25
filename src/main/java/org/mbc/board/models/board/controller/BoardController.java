package org.mbc.board.models.board.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mbc.board.models.board.dto.PageRequestDTO;
import org.mbc.board.models.board.service.IBoardService;
import org.mbc.board.models.board.service.IBoardServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Transactional
@Log4j2
public class BoardController {

    private final IBoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDTO request, Model model) {

        var response = boardService.getBoardPage(request);

        log.info(response);

        model.addAttribute("response", response);
    }
}

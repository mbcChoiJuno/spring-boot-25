package com.mbc.juno.models.board.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board")
public class BoardController {

    @GetMapping("/boards")
    public ResponseEntity getBoardList() {
        return (ResponseEntity) ResponseEntity.ok();
    }
}

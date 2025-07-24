package org.mbc.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mbc.board.dto.PageRequestDTO;
import org.mbc.board.dto.PageResponseDTO;
import org.mbc.board.dto.ReplyDTO;
import org.mbc.board.service.ReplyService;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    /*@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Long>> register(
            @RequestBody ReplyDTO request) {

        log.info(request.toString());

        var result = Map.of("rno", 111L);
        return ResponseEntity.ok(result);
    }*/

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> register(
            @Valid @RequestBody ReplyDTO replyDTO,
            BindingResult bindingResult) throws BindException {

        log.info(replyDTO.toString());

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        Map<String, Long> resultMap = new HashMap<>();
        Long rno = replyService.register(replyDTO);

        resultMap.put("rno", rno);

        return resultMap;
    }

    @GetMapping(value = "/list/{bno}")
    public PageResponseDTO<ReplyDTO> getList(
            @PathVariable
            Long bno,
            PageRequestDTO pageRequestDTO) {
        PageResponseDTO<ReplyDTO> responseDTO = replyService.getListOfBoard(bno, pageRequestDTO);

        return responseDTO;
    }

    @GetMapping("/{rno}")
    public ReplyDTO getReplyDTO(
            @PathVariable
            Long rno) {
        return replyService.read(rno);
    }

    @PutMapping(value = "/{rno}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> remove(
            @PathVariable
            Long rno,
            @RequestBody
            ReplyDTO replyDTO) {

        replyDTO.setRno(rno);
        replyService.modify(replyDTO);

        return Map.of("rno", rno);
    }

    @DeleteMapping("/{rno}")
    public Map<String, Long> remove(
            @PathVariable
            Long rno) {

        replyService.remove(rno);

        return Map.of("rno", rno);
    }
}



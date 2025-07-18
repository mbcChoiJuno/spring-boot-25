package org.mbc.board.models.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long boardIndex;

    private String title;
    private String content;
    private String writer;
    private LocalDateTime createDate;
    private LocalDateTime latestDate;
    private String deletedYn;
}

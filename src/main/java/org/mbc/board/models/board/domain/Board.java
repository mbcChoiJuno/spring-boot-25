package org.mbc.board.models.board.domain;

import jakarta.persistence.*;
import lombok.*;
import org.mbc.board.core.BaseEntity;

@Entity
@Table(name = "board_tbl")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board extends BaseEntity {

    @Id
    @Column(name = "board_index")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardIndex;

    @Column(name = "board_title",
            length = 500)
    private String title;

    @Column(name = "board_content",
            length = 2000)
    private String content;

    @Column(name = "board_writer",
            length = 50)
    private String writer;
}

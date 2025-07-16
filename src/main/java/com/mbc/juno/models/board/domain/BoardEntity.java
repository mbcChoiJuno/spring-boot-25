package com.mbc.juno.models.board.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "board_tbl")
public class BoardEntity {

    @Id
    private Long board_no;
}

package org.mbc.board.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "memo_tbl")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @Column(length = 200, nullable = false)
    private String memoText;
}





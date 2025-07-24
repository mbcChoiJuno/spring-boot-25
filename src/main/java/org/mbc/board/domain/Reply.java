package org.mbc.board.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table( name = "reply",
		indexes = {
			@Index(name = "idx_reply_board_bno", columnList = "board_bno")
		})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "board")
public class Reply extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rno;

	@ManyToOne(fetch = FetchType.LAZY)
	private Board board;

	private String replyText;

	private String replyer;

}

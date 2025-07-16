package org.mbc.board.core;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// 공통 최상위 클래스 명시
@MappedSuperclass
// 리스너 추가 -> 최초생성:@CreateDate / 수정:@LastModifiedDate
@EntityListeners(value = {
    AuditingEntityListener.class
})
@Getter
public abstract class BaseEntity {

    @CreatedDate
    @Column(name = "create_date", updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(name = "latest_date")
    private LocalDateTime latestDate;


}

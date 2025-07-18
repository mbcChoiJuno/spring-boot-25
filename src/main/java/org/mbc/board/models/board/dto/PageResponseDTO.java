package org.mbc.board.models.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<T> {

    private int page;
    private int size;
    private int total;

    private int navStart;

    private int navEnd;

    private boolean hasPrev;
    private boolean hasNext;

    private List<T> items;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO request, List<T> items, int total) {
        if (0 > total)
            return;

        this.total = total;
        this.items = items;

        page = request.getPage();
        size = request.getSize();

        // 1-10 ,, 11-20 ,, 21-30
        navEnd = (int)(Math.ceil((double) page / 10)) * 10;
        navStart = navEnd - 9;

        navEnd = navEnd > items.size() ? items.size() : navEnd;

        hasPrev = (navStart > 1);
        hasNext = (total > navEnd * size);

        request.getLink();
    }
}

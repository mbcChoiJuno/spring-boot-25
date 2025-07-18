package org.mbc.board.models.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * endpoint : /api/boards
 * endpoint : /api/boards?page=11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
public class PageRequestDTO {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

    private String type;

    private String keyword;

    private String link;

    public String[] getTypes() {
        if (type == null || type.isEmpty())
            return null;
        return type.split(" ");
    }

    public Pageable getPageable(String... props) {
        int page = this.page - 1;
        int size = this.size;
        return PageRequest.of(page, size, Sort.by(props).descending());
    }

    public String getLink() {
        StringBuilder sb = new StringBuilder();

        if (link == null) {
            sb.append("?page=");
            sb.append(this.page);

            sb.append("&size=");
            sb.append(this.size);

            String[] types = getTypes();
            if (types != null && types.length > 0) {
                sb.append("&type=");
                sb.append(type);
            }

            if (keyword != null) {
                String encKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8);
                sb.append("&keyword=");
                sb.append(encKeyword);
            }

            link = sb.toString();
        }

        return link;
    }
}

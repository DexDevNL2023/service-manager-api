package net.service.manager.career.generic.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagedResponse<R extends BaseReponse> {
    private List<R> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;
}

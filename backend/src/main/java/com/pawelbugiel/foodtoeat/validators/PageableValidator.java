package com.pawelbugiel.foodtoeat.validators;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PageableValidator {

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int MAX_PAGE_SIZE = 50;
    private static final String DEFAULT_SORT_BY = "name";
    private static final Sort.Direction DEFAULT_SORT_DIRECTION = Sort.Direction.ASC;

    public Pageable validatePageable(Integer page, Integer pageSize, String sortBy, Sort.Direction sortDirection) {
        int safePage = (page != null && page >= 0) ? page : DEFAULT_PAGE;
        int safePageSize = (pageSize != null && pageSize > 0 && pageSize <= MAX_PAGE_SIZE) ? pageSize : DEFAULT_PAGE_SIZE;
        String safeSortBy = (sortBy != null) ? sortBy : DEFAULT_SORT_BY;
        Sort.Direction safeSortDirection = (sortDirection != null) ? sortDirection : DEFAULT_SORT_DIRECTION;

        return PageRequest.of(safePage, safePageSize, Sort.by(safeSortDirection, safeSortBy));
    }
}

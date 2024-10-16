package com.pawelbugiel.foodtoeat.dtos;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class QueryParams {

    @Pattern(regexp = "^[0-9]*$", message = "Page must be a non-negative integer")
    private String page;
    private Sort.Direction sortDirection;
    private String sortBy;


    public QueryParams(String page, Sort.Direction sortDirection, String sortBy) {
        this.page = page;
        this.sortDirection = sortDirection;
        this.sortBy = sortBy;
    }

}


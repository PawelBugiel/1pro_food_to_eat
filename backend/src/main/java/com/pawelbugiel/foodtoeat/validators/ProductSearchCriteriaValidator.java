package com.pawelbugiel.foodtoeat.validators;

import com.pawelbugiel.foodtoeat.dtos.QueryParams;
import com.pawelbugiel.foodtoeat.models.ProductProperties;
import org.springframework.data.domain.Sort;

public class ProductSearchCriteriaValidator {

    private final static String DEFAULT_PAGE_NUMBER = "0";

    public static QueryParams valid(QueryParams criteria) {
        if (criteria == null) {
            return new QueryParams(DEFAULT_PAGE_NUMBER, Sort.Direction.ASC, ProductProperties.EXPIRY_DATE.getValue());
        } else {
            var startPage = PageValidator.getValidPage(criteria.getPage());
            var aValidDirection = SortDirectionValidator.validDirection(criteria.getSortDirection());
            var sortByValue = SortByValidator.valid(criteria.getSortBy());
        return new QueryParams(String.valueOf(startPage), aValidDirection, sortByValue);
        }

    }
}
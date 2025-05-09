package com.comitfy.kidefy.util.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class BaseFilterRequestDTO {
    private List<SearchCriteria> filters;
    private int pageNumber;
    private int pageSize;
    @JsonIgnore
    private UUID branchUUID;
    @JsonIgnore
    private String sortBy;
    private SortType sortType;
}

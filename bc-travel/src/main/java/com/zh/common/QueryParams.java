package com.zh.common;

import lombok.Data;

import java.util.List;

@Data
public class QueryParams {
    Integer pageNum;
    Integer pageSize;
    List<String> conditions;
}

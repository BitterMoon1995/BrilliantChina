package com.zh.common.util;

import lombok.Data;

import java.util.List;

@Data
public class QueryParams {
    Integer pageNum;
    Integer pageSize;
    List<String> conditions;
}

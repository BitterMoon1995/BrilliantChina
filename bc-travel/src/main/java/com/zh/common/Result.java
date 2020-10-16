package com.zh.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    //分页的类设计......GG
//    public Integer total;
    public Object data;
    public Info info;
}

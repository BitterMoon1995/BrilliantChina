package com.zh.mini.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResCode {
    public Integer code;
    public String message;
    public Object tar;
}

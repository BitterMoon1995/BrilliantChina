package com.zh.mini.vo;

import com.zh.mini.bo.StickyObject;
import lombok.Data;

import java.util.List;
@Data
public class StickyObjectVo {
    List<StickyObject> stickyList;
    Integer total;
}

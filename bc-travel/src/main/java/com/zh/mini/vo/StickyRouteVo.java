package com.zh.mini.vo;

import com.zh.mini.bo.StickyRoute;
import com.zh.mini.bo.StickyScene;
import lombok.Data;

import java.util.List;
@Data
public class StickyRouteVo {
    List<StickyRoute> stickyList;
    Integer total;
}

package com.zh.mini.vo;

import com.zh.mini.entity.Route;
import com.zh.mini.entity.Scene;
import lombok.Data;

import java.util.List;

@Data
public class RouteVo {
    List<Route> routeList;
    Integer total;
}

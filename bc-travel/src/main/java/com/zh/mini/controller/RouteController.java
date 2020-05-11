package com.zh.mini.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.mini.entity.Route;
import com.zh.mini.entity.RouteImage;
import com.zh.mini.service.IRouteImageService;
import com.zh.mini.service.IRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 海琴烟
 * @since 2020-03-31
 */
@RestController
@CrossOrigin
@RequestMapping("/mini/route")
public class RouteController {
    @Autowired
    IRouteService service;
    @PostMapping("/saveRoute")
    public void saveRoute(@RequestBody Route route){
        service.saveRoute(route);
    }
    @PostMapping("/updateRoute")
    public void updateRoute(@RequestBody Route route){
        service.updateById(route);
    }
}

package com.zh.mini.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.mini.entity.RouteImage;
import com.zh.mini.service.IRouteImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/mini/route-image")
public class RouteImageController {
    @Autowired
    IRouteImageService service;
    @RequestMapping("/addInBulk")
    public void addInBulk(@RequestBody List<RouteImage> images){
        if (images.size()==4) {
            QueryWrapper<RouteImage> wrapper = new QueryWrapper<>();
            service.remove(wrapper);
            service.saveBatch(images);
        }
    }
    @RequestMapping("/add")
    public void add(@RequestBody RouteImage image){
        service.save(image);
    }
    @RequestMapping("/list")
    public List<RouteImage> list(){
        QueryWrapper<RouteImage> wrapper = new QueryWrapper<>();
        wrapper.eq("type","homePage");
        wrapper.orderByAsc("order_num");
        return service.list(wrapper);
    }
}

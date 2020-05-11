package com.zh.mini.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.mini.entity.ActivityImage;
import com.zh.mini.service.IActivityImageService;
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
@RequestMapping("/mini/activity-image")
public class ActivityImageController {
    @Autowired
    IActivityImageService service;
    @RequestMapping("/addInBulk")
    public void addInBulk(@RequestBody List<ActivityImage> images){
        if (images.size()==4) {
            QueryWrapper<ActivityImage> wrapper = new QueryWrapper<>();
            service.remove(wrapper);
            service.saveBatch(images);
        }
    }
    @RequestMapping("/add")
    public void add(@RequestBody ActivityImage image){
        service.save(image);
    }
    @RequestMapping("/list")
    public List<ActivityImage> list(){
        QueryWrapper<ActivityImage> wrapper = new QueryWrapper<>();
        wrapper.eq("type","homePage");
        wrapper.orderByAsc("order_num");
        return service.list(wrapper);
    }
}

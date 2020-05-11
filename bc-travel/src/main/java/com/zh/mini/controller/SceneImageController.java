package com.zh.mini.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.mini.common.ResCode;
import com.zh.mini.entity.Category;
import com.zh.mini.entity.SceneImage;
import com.zh.mini.entity.Swiper;
import com.zh.mini.service.ICategoryService;
import com.zh.mini.service.ISceneImageService;
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
@RequestMapping("/mini/scene-image")
public class SceneImageController {
    @Autowired
    ISceneImageService service;
    @RequestMapping("/addInBulk")
    public void addInBulk(@RequestBody List<SceneImage> images){
        if (images.size()==4) {
            QueryWrapper<SceneImage> wrapper = new QueryWrapper<>();
            service.remove(wrapper);
            service.saveBatch(images);
        }
    }
    @RequestMapping("/add")
    public void add(@RequestBody SceneImage image){
        service.save(image);
    }
    @RequestMapping("/list")
    public List<SceneImage> list(){
        QueryWrapper<SceneImage> wrapper = new QueryWrapper<>();
        wrapper.eq("type","homePage");
        wrapper.orderByAsc("order_num");
        return service.list(wrapper);
    }
}

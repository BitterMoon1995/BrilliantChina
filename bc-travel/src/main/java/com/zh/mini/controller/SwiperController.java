package com.zh.mini.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.mini.common.ResCode;
import com.zh.mini.entity.Swiper;
import com.zh.mini.service.ISwiperService;
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
@RequestMapping("/mini/swiper")
public class SwiperController {
    @Autowired
    ISwiperService swiperService;
    @RequestMapping("/add")
    public ResCode add(@RequestBody Swiper swiper){
        int count = swiperService.count();
        if (count<4){
            swiperService.save(swiper);
            return new ResCode(200,"插入成功",null);
        }
        else return new ResCode(500,"首页轮播图不能超过5张",null);
    }
    @RequestMapping("/addInBulk")
    public void addInBulk(@RequestBody List<Swiper> swipers){
        int count = swiperService.count();
        if (swipers.size()==4){
            QueryWrapper<Swiper> wrapper = new QueryWrapper<>();
            swiperService.remove(wrapper);
            swiperService.saveBatch(swipers);
        }
    }
    @RequestMapping("/remove")
    public void remove(String id){
        swiperService.removeById(id);
    }
    @RequestMapping("/list")
    public List<Swiper> list(){
        return swiperService.list();
    }
}

package com.zh.mini.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.mini.entity.Slider;
import com.zh.mini.service.ISliderService;
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
@RequestMapping("/mini/slider")
public class SliderController {
    @Autowired
    ISliderService service;
    //出发 巴拉巴拉巴
    @RequestMapping("/list")
    public List<Slider> list(){
        QueryWrapper<Slider> wrapper = new QueryWrapper<>();
        wrapper.eq("top",true).orderByAsc("order_num");
        return service.list(wrapper);
    }
}

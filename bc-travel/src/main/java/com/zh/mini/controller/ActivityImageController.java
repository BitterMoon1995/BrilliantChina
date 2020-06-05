package com.zh.mini.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.mini.entity.ActivityImage;
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
    //周神之铃鹿御前
    @RequestMapping("/getSticky")
    public List<ActivityImage> getSticky() {
        QueryWrapper<ActivityImage> wrapper = new QueryWrapper<>();
        wrapper.eq("type", "postcard")
                //只取置顶的，排好序
                .eq("top",true)
                .orderByAsc("order_num")
                //.last 拼接末尾的SQL语句，这里用limit限制取出的数量，只取4个
                .last("limit 0,4");
        return service.list(wrapper);
    }

    @RequestMapping("/getIntros")
    public List<ActivityImage> getIntros(String activityId) {
        QueryWrapper<ActivityImage> wrapper = new QueryWrapper<>();
        wrapper.eq("type", "intros")
                .eq("activity_id", activityId)
                .orderByAsc("order_num");
        return service.list(wrapper);
    }

    @RequestMapping("/getRichText")
    public ActivityImage getRichText(String activityId) {
        QueryWrapper<ActivityImage> wrapper = new QueryWrapper<>();
        wrapper.eq("type", "richText")
                .eq("activity_id", activityId);
        return service.getOne(wrapper);
    }
}

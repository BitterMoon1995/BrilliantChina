package com.zh.mini.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.mini.entity.Category;
import com.zh.mini.entity.RouteImage;
import com.zh.mini.entity.SceneImage;
import com.zh.mini.entity.Slider;
import com.zh.mini.service.ICategoryService;
import com.zh.mini.service.ISceneImageService;
import com.zh.mini.vo.StickyImgVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
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

    //周神之铃鹿御前
    @RequestMapping("/getFloorList")
    public List<StickyImgVo> getFloorList() {
        return service.getFloorList();
    }

    @RequestMapping("/getIntros")
    public List<SceneImage> getIntros(String sceneId) {
        QueryWrapper<SceneImage> wrapper = new QueryWrapper<>();
        wrapper.eq("type", "intros")
                .eq("scene_id", sceneId)
                .orderByAsc("order_num");
        return service.list(wrapper);
    }

    @RequestMapping("/getRichText")
    public SceneImage getRichText(String sceneId) {
        QueryWrapper<SceneImage> wrapper = new QueryWrapper<>();
        wrapper.eq("type", "richText")
                .eq("scene_id", sceneId);
        return service.getOne(wrapper);
    }
}

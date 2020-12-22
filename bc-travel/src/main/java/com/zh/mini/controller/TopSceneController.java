package com.zh.mini.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zh.common.Info;
import com.zh.common.Result;
import com.zh.common.Status;
import com.zh.common.iResult;
import com.zh.mini.bo.StickyObject;
import com.zh.mini.entity.SceneImage;
import com.zh.mini.entity.Slider;
import com.zh.mini.service.ISceneImageService;
import com.zh.mini.service.ISceneService;
import com.zh.mini.service.ISliderService;
import com.zh.mini.vo.StickyObjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/mini/scene")
public class TopSceneController {

    @Autowired
    ISceneImageService imageService;
    @Autowired
    ISliderService sliderService;
    @Autowired
    ISceneService service;

    @GetMapping("/getSticky")
    public StickyObjectVo getSticky(@RequestParam Integer pageNum, @RequestParam Integer pageSize,
                                    @RequestParam String condition){
        StickyObjectVo sceneStickyVo = new StickyObjectVo();
        List<StickyObject> stickyList;

        if (condition.isEmpty()) {
            stickyList = service.getSticky((pageNum - 1) * pageSize, pageSize);
        }
        else {
            stickyList = service.search((pageNum - 1) * pageSize, pageSize,condition);
        }
        int total = service.count();
        sceneStickyVo.setStickyList(stickyList);
        sceneStickyVo.setTotal(total);
        return sceneStickyVo;
    }

    @PostMapping("/change")
    public iResult change(@RequestBody StickyObject stickyObject){

        QueryWrapper<Slider> qOriginal = new QueryWrapper<>();
        qOriginal.eq("id",stickyObject.getSliderId());
        Slider originalSlider = sliderService.getOne(qOriginal);

        //判断是否是轮播图的更新操作
        if (originalSlider.getTop() != stickyObject.getSliderTop()){
            //是轮播图的更新操作（而不是名片图）。计算已经置顶的轮播图
            QueryWrapper<Slider> sliderQW = new QueryWrapper<>();
            sliderQW.eq("top",true);
            int count = sliderService.count(sliderQW);
            //已经置顶的轮播图已经有4张了，而这次又要再置顶一个，报错
            if (count >= 4 && stickyObject.getSliderTop()) {
                return new iResult(new Status("置顶数不能超过四个",Status.illegalParam));
            }
            //要么置顶数没满4个，要么是这一次是取消置顶的操作，操作合法，执行更新操作
            else {
                UpdateWrapper<Slider> sliderUW = new UpdateWrapper<>();
                sliderUW.eq("id",stickyObject.getSliderId());

                Slider slider = sliderService.getById(stickyObject.getSliderId());
                slider.setTop(stickyObject.getSliderTop());
                slider.setOrderNum(stickyObject.getSliderOrder());
                slider.setUrl(stickyObject.getUrl());
                sliderService.update(slider,sliderUW);
                return iResult.success;
            }
        }

        //走到这里就必然是名片图的更新。
        //校验楼层图，置顶的数量不能大于4
        QueryWrapper<SceneImage> imageQW = new QueryWrapper<>();
        imageQW.eq("top",true).eq("type","postcard");
        int topPostcardNum = imageService.count(imageQW);
        if (topPostcardNum>=4 && stickyObject.getStickyTop()){
            return new iResult(new Status("置顶数不能超过四个",Status.illegalParam));
        }
        //更新名片
        UpdateWrapper<SceneImage> sceneUW= new UpdateWrapper<>();
        sceneUW.eq("id", stickyObject.getImgId());

        SceneImage sceneImage = imageService.getById(stickyObject.getImgId());
        sceneImage.setTop(stickyObject.getStickyTop());
        sceneImage.setOrderNum(stickyObject.getStickyOrder());
        sceneImage.setUrl(stickyObject.getUrl());
        imageService.update(sceneImage,sceneUW);
        return iResult.success;
    }
}

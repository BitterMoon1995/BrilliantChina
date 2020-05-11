package com.zh.mini.controller;


import com.zh.mini.entity.Activity;
import com.zh.mini.service.IActivityService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/mini/activity")
@Api
public class ActivityController {
    @Autowired
    IActivityService activityService;
    @PostMapping("/addAct")
    public void addAct(@RequestBody Activity activity){
        activityService.addAct(activity);
    }
    @RequestMapping("/getAct")
    public Activity getAct(@RequestParam("actId") String id){
        return activityService.getAct(id);
    }
}

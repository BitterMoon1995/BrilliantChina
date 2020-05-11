package com.zh.mini.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.mini.entity.Scene;
import com.zh.mini.service.ISceneService;
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
@RequestMapping("/mini/scene")
public class SceneController {
    @Autowired
    ISceneService service;
    @PostMapping("/saveScene")
    public void saveScene(@RequestBody Scene scene){
        service.saveScene(scene);
    }
    @PostMapping("/updateScene")
    public void updateScene(@RequestBody Scene scene){
        service.updateById(scene);
    }
    @GetMapping("/list")
    public List<Scene> list(){
        return service.list();
    }

    @GetMapping("/getByUsername")
    public List<Scene> getByUsername(@RequestParam String username){
        QueryWrapper<Scene> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        return service.list(wrapper);
    }
}

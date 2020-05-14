package com.zh.mini.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.mini.entity.Scene;
import com.zh.mini.service.ISceneService;
import com.zh.mini.vo.SceneVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.Result;

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

    //周神之：归来

    @GetMapping("/getByUsername")
    public SceneVo getByUsername(@RequestParam Integer pageNum, @RequestParam Integer pageSize
            , @RequestParam String condition , @RequestParam String username){
        SceneVo sceneVo = new SceneVo();
        Page<Scene> page = new Page<>(pageNum,pageSize);
        //非搜索
        if (condition.isEmpty()) {
            //非管理员根据username查
            if (!username.equals("admin") && !username.equals("manager")) {
                QueryWrapper<Scene> wrapper = new QueryWrapper<>();
                wrapper.eq("username", username);
                List<Scene> list = service.page(page,wrapper).getRecords();
                sceneVo.setSceneList(list);
                //一定要返回真正的总数量
                sceneVo.setTotal(service.count(wrapper));
            //管理员，返回所有
            } else {
                List<Scene> list = service.page(page).getRecords();
                sceneVo.setSceneList(list);
                sceneVo.setTotal(service.count());
            }
        }
        return sceneVo;
    }
    @GetMapping("/resetOrder")
    public void resetOrder(){
        service.resetOrder();
    }

    @PostMapping("/addScene")
    public Result addScene(@RequestBody Scene scene){
        service.saveScene(scene);
        System.out.print(scene.toString());
        return null;
    }
}

package com.zh.mini.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.mini.entity.Scene;
import com.zh.mini.service.ISceneService;
import com.zh.mini.vo.SceneVo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.Info;
import util.QueryParams;
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
        //搜索
        else {
            //非管理员根据username查
            List<Scene> sceneList;
            if (!username.equals("admin") && !username.equals("manager")) {
                sceneList = service.search(username,condition);
                //一定要返回真正的总数量
            } else {
                sceneList = service.allSearch(condition);
            }
            sceneVo.setSceneList(sceneList);
            sceneVo.setTotal(sceneList.size());
        }
        return sceneVo;
    }
    @GetMapping("/resetOrder")
    public void resetOrder(){
        service.resetOrder();
    }

    @PostMapping("/add")
    public Result add(@RequestBody Scene scene){
        Result result = new Result(new Object(),new Info());
        String name = scene.getName();
        QueryWrapper<Scene> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        List<Scene> list = service.list(wrapper);
//        不重名 ID为空 说明是新增
        if (list.size()==0 && scene.getId()==null){
            service.saveScene(scene);
            result.data = null;
            result.info.setCode(200);
            result.info.setMsg("新增景区成功！");
            return result;
        }
//        重名 ID不空 说明修改
        if (list.size()==1 && !scene.getId().isEmpty()){
            service.editScene(scene);
            result.data = null;
            result.info.setCode(200);
            result.info.setMsg("编辑景区成功！");
        }
        else {
            result.data=null;
            result.info.setCode(400);
            result.info.setMsg("景区名已存在！");
        }
        return result;
    }

    @GetMapping("/getScene")
    public Scene get(@RequestParam String id,@RequestParam boolean img){
        Scene scene = service.getById(id);
        if (img)
        service.setImgs(scene,id);
        return scene;
    }

    @DeleteMapping("/del")
    public boolean delScene(@RequestParam String id){
        service.delDetails(id);
        return service.removeById(id);
    }
}

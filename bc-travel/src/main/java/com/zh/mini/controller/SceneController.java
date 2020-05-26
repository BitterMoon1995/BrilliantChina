package com.zh.mini.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.mini.entity.Scene;
import com.zh.mini.entity.Scene;
import com.zh.mini.entity.SceneImage;
import com.zh.mini.entity.Slider;
import com.zh.mini.service.ISceneImageService;
import com.zh.mini.service.ISceneService;
import com.zh.mini.service.ISliderService;
import com.zh.mini.bo.StickyScene;
import com.zh.mini.vo.StickySceneVo;
import com.zh.mini.vo.SceneVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.Info;
import util.Result;

import java.util.Date;
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
        result.setData(null);
        String name = scene.getName();
        QueryWrapper<Scene> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        List<Scene> list = service.list(wrapper);
//        ID为空 说明是新增
        if (scene.getId()==null){
//            重名，报错
            if (list.size()!=0){
                result.info.setCode(400);
                result.info.setMsg("景区名已存在！");
                return result;
            }
            //设置创建时间
            scene.setCreateTime(new Date());
            service.add(scene);
            result.info.setCode(200);
            result.info.setMsg("新增景区成功！");
        }
//        ID不为空就是修改，修改不检查重名！！！！！！！
        else {
            service.edit(scene);
            result.info.setCode(200);
            result.info.setMsg("编辑景区成功！");
        }
        service.resetOrder();
        return result;
    }

    @GetMapping("/getScene")
    public Scene get(@RequestParam String id,@RequestParam boolean img){
        Scene scene = service.getById(id);
        if (img)
        service.setImgs(scene,id);
        return scene;
    }
//    因为公司在服务器租赁上的预算较少，服务器的总硬盘容量可能只有50G
//    所以所有的删除操作都采取物理删除
    @DeleteMapping("/del")
    public boolean delScene(@RequestParam String id){
        service.delDetails(id);
        return service.removeById(id);
    }

    @GetMapping("/getSticky")
    public StickySceneVo getSticky(@RequestParam Integer pageNum, @RequestParam Integer pageSize,
                                  @RequestParam String condition){
        StickySceneVo sceneStickyVo = new StickySceneVo();
        List<StickyScene> stickyList;

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

    @Autowired
    ISceneImageService imageService;
    @Autowired
    ISliderService sliderService;

    @PostMapping("/change")
    public Result change(@RequestBody StickyScene stickyScene){

        Result result = new Result(new Object(), new Info());

        //更新名片
        UpdateWrapper<SceneImage> sceneUW= new UpdateWrapper<>();
        sceneUW.eq("id", stickyScene.getImgId());

        SceneImage sceneImage = imageService.getById(stickyScene.getImgId());
        sceneImage.setTop(stickyScene.getStickyTop());
        sceneImage.setOrderNum(stickyScene.getStickyOrder());
        sceneImage.setUrl(stickyScene.getUrl());
        imageService.update(sceneImage,sceneUW);

        //更新首页轮播图
        UpdateWrapper<Slider> sliderUW = new UpdateWrapper<>();
        sliderUW.eq("id",stickyScene.getSliderId());

        Slider slider = sliderService.getById(stickyScene.getSliderId());
        slider.setTop(stickyScene.getSliderTop());
        slider.setOrderNum(stickyScene.getSliderOrder());
        slider.setUrl(stickyScene.getUrl());
        sliderService.update(slider,sliderUW);

        //校验轮播图，置顶的数量不能大于4
        QueryWrapper<Slider> sliderQW = new QueryWrapper<>();
        sliderQW.eq("top",true);
        int count = sliderService.count(sliderQW);

        if (count>4){
            result.data=null;
            result.info.setCode(400);
            result.info.setMsg("轮播图置顶数量不能超过四个！");
            return result;
        }

        result.data=null;
        result.info.setCode(200);
        return result;
    }

}

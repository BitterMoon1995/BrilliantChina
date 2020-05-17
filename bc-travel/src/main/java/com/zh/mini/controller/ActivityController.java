package com.zh.mini.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.mini.entity.Activity;
import com.zh.mini.entity.Activity;
import com.zh.mini.service.IActivityService;
import com.zh.mini.vo.ActivityVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.Info;
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
@RequestMapping("/mini/activity")
@Api
public class ActivityController {
    @Autowired
    IActivityService service;
    @GetMapping("/getByUsername")
    public ActivityVo getByUsername(@RequestParam Integer pageNum, @RequestParam Integer pageSize
            , @RequestParam String condition , @RequestParam String username){
        ActivityVo activityVo = new ActivityVo();
        Page<Activity> page = new Page<>(pageNum,pageSize);
        //非搜索
        if (condition.isEmpty()) {
            //非管理员根据username查
            if (!username.equals("admin") && !username.equals("manager")) {
                QueryWrapper<Activity> wrapper = new QueryWrapper<>();
                wrapper.eq("username", username);
                List<Activity> list = service.page(page,wrapper).getRecords();
                activityVo.setActivityList(list);
                //一定要返回真正的总数量
                activityVo.setTotal(service.count(wrapper));
                //管理员，返回所有
            } else {
                List<Activity> list = service.page(page).getRecords();
                activityVo.setActivityList(list);
                activityVo.setTotal(service.count());
            }
        }
        //搜索
        else {
            //非管理员根据username查
            List<Activity> activityList;
            if (!username.equals("admin") && !username.equals("manager")) {
                activityList = service.search(username,condition);
                //一定要返回真正的总数量
            } else {
                activityList = service.allSearch(condition);
            }
            activityVo.setActivityList(activityList);
            activityVo.setTotal(activityList.size());
        }
        return activityVo;
    }
    @GetMapping("/resetOrder")
    public void resetOrder(){
        service.resetOrder();
    }

    @PostMapping("/add")
    public Result add(@RequestBody Activity activity){
        Result result = new Result(new Object(),new Info());
        String name = activity.getName();
        QueryWrapper<Activity> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        List<Activity> list = service.list(wrapper);
//        不重名 ID为空 说明是新增
        if (list.size()==0 && activity.getId()==null){
            service.saveActivity(activity);
            result.data = null;
            result.info.setCode(200);
            result.info.setMsg("新增景区成功！");
            return result;
        }
//        重名 ID不空 说明修改
        if (list.size()==1 && !activity.getId().isEmpty()){
            service.editActivity(activity);
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

    @GetMapping("/getActivity")
    public Activity get(@RequestParam String id,@RequestParam boolean img){
        Activity activity = service.getById(id);
        if (img)
            service.setImgs(activity,id);
        return activity;
    }

    @DeleteMapping("/del")
    public boolean delActivity(@RequestParam String id){
        service.delDetails(id);
        return service.removeById(id);
    }
}

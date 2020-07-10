package com.zh.mini.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.admin.entity.User;
import com.zh.admin.service.IUserService;
import com.zh.mini.bo.SearchResult;
import com.zh.mini.bo.StickyObject;
import com.zh.mini.entity.*;
import com.zh.mini.entity.Activity;
import com.zh.mini.service.IActivityService;
import com.zh.mini.service.IActivityImageService;
import com.zh.mini.service.ISliderService;
import com.zh.mini.vo.ActivityVo;
import com.zh.mini.vo.StickyObjectVo;
import io.swagger.annotations.Api;
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
@RequestMapping("/mini/activity")
@Api
public class ActivityController {
    @Autowired
    IActivityService service;
    @Autowired
    IUserService userService;
    @GetMapping("/getByUsername")
    public ActivityVo getByUsername(@RequestParam Integer pageNum, @RequestParam Integer pageSize
            , @RequestParam String condition , @RequestParam String username){
        ActivityVo activityVo = new ActivityVo();
        Page<Activity> page = new Page<>(pageNum,pageSize);
        //获取用户
        User user = getUser(username);
        //非搜索
        if (condition.isEmpty()) {
            //非管理员根据username查
            if (user.getRole() == 3) {
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
        result.setData(null);
        String name = activity.getName();
        QueryWrapper<Activity> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        List<Activity> list = service.list(wrapper);
//        ID为空 说明是新增
        if (activity.getId()==null){
//            重名，报错
            if (list.size()!=0){
                result.info.setCode(400);
                result.info.setMsg("景区名已存在！");
                return result;
            }
            //设置创建时间
            activity.setCreateTime(new Date());
            service.add(activity);
            result.info.setCode(200);
            result.info.setMsg("新增景区成功！");
        }
//        ID不为空就是修改，修改不检查重名！！！！！！！
        else {
            Integer edit = service.edit(activity);
            if (edit == 400){
                result.info.setCode(400);
                result.info.setMsg("活动名已存在！");
            }
            else {
                result.info.setCode(200);
                result.info.setMsg("编辑活动成功！");
            }
        }
        service.resetOrder();
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

    @GetMapping("/getSticky")
    public StickyObjectVo getSticky(@RequestParam Integer pageNum, @RequestParam Integer pageSize,
                                   @RequestParam String condition){
        StickyObjectVo activityStickyVo = new StickyObjectVo();
        List<StickyObject> stickyList;

        if (condition.isEmpty()) {
            stickyList = service.getSticky((pageNum - 1) * pageSize, pageSize);
        }
        else {
            stickyList = service.search((pageNum - 1) * pageSize, pageSize,condition);
        }
        int total = service.count();
        activityStickyVo.setStickyList(stickyList);
        activityStickyVo.setTotal(total);
        return activityStickyVo;
    }

    @Autowired
    IActivityImageService imageService;
    @Autowired
    ISliderService sliderService;

    @PostMapping("/change")
    public Result change(@RequestBody StickyObject stickyObject){

        Result result = new Result(new Object(), new Info());

        //校验轮播图，置顶的数量不能大于4。先计算已置顶的轮播图数量
        QueryWrapper<Slider> sliderQW = new QueryWrapper<>();
        sliderQW.eq("top",true);
        int count = sliderService.count(sliderQW);

        //再判断这一次操作，是否是置顶新轮播图的操作
        QueryWrapper<Slider> qOriginal = new QueryWrapper<>();
        qOriginal.eq("id",stickyObject.getSliderId());
        Slider originalSlider = sliderService.getOne(qOriginal);

        //也就是这次操作属于对楼层图置顶的操作，而且是原先没有置顶该楼层图，而这次要置顶。
        //否则的话会干扰其他所有操作
        if (!originalSlider.getTop() && stickyObject.getSliderTop() && count>=4){
            result.data=null;
            result.info.setCode(400);
            result.info.setMsg("轮播图置顶数量不能超过四个！");
            return result;
        }

        //校验楼层图，置顶的数量不能大于4
        QueryWrapper<ActivityImage> imageQW = new QueryWrapper<>();
        imageQW.eq("top",true).eq("type","postcard");
        int count1 = imageService.count(imageQW);

        imageQW.clear();
        imageQW.eq("id",stickyObject.getImgId());
        ActivityImage originalImg = imageService.getOne(imageQW);

        if (count1>=4 && stickyObject.getStickyTop() && !originalImg.getTop()){
            result.data=null;
            result.info.setCode(400);
            result.info.setMsg("各个楼层图置顶数量不能超过四个！");
            return result;
        }

        //更新名片
        UpdateWrapper<ActivityImage> activityUW= new UpdateWrapper<>();
        activityUW.eq("id", stickyObject.getImgId());

        ActivityImage activityImage = imageService.getById(stickyObject.getImgId());
        activityImage.setTop(stickyObject.getStickyTop());
        activityImage.setOrderNum(stickyObject.getStickyOrder());
        activityImage.setUrl(stickyObject.getUrl());
        imageService.update(activityImage,activityUW);

        //更新首页轮播图
        UpdateWrapper<Slider> sliderUW = new UpdateWrapper<>();
        sliderUW.eq("id",stickyObject.getSliderId());

        Slider slider = sliderService.getById(stickyObject.getSliderId());
        slider.setTop(stickyObject.getSliderTop());
        slider.setOrderNum(stickyObject.getSliderOrder());
        slider.setUrl(stickyObject.getUrl());
        sliderService.update(slider,sliderUW);

        result.data=null;
        result.info.setCode(200);
        return result;
    }

    @GetMapping("/search")
    public List<SearchResult> search(@RequestParam String condition){
        return service.search(condition);
    }

    //根据用户名返回用户
    public User getUser(String username){
        QueryWrapper<User> userQ = new QueryWrapper<>();
        userQ.eq("username",username);
        return userService.getOne(userQ);
    }
}

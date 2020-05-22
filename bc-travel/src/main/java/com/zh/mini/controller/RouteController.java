package com.zh.mini.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.mini.bo.StickyRoute;
import com.zh.mini.entity.*;
import com.zh.mini.entity.Route;
import com.zh.mini.service.*;
import com.zh.mini.service.IRouteService;
import com.zh.mini.vo.RouteVo;
import com.zh.mini.vo.StickyRouteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.Info;
import util.Result;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
@RequestMapping("/mini/route")
public class RouteController {
    @Autowired
    IRouteService service;
    //周神之：归来

    @GetMapping("/getByUsername")
    public RouteVo getByUsername(@RequestParam Integer pageNum, @RequestParam Integer pageSize
            , @RequestParam String condition , @RequestParam String username){
        RouteVo routeVo = new RouteVo();
        Page<Route> page = new Page<>(pageNum,pageSize);
        //非搜索
        if (condition.isEmpty()) {
            //非管理员根据username查
            if (!username.equals("admin") && !username.equals("manager")) {
                QueryWrapper<Route> wrapper = new QueryWrapper<>();
                wrapper.eq("username", username);
                List<Route> list = service.page(page,wrapper).getRecords();
                routeVo.setRouteList(list);
                //一定要返回真正的总数量
                routeVo.setTotal(service.count(wrapper));
                //管理员，返回所有
            } else {
                List<Route> list = service.page(page).getRecords();
                routeVo.setRouteList(list);
                routeVo.setTotal(service.count());
            }
        }
        //搜索
        else {
            //非管理员根据username查
            List<Route> routeList;
            if (!username.equals("admin") && !username.equals("manager")) {
                routeList = service.search(username,condition);
                //一定要返回真正的总数量
            } else {
                routeList = service.allSearch(condition);
            }
            routeVo.setRouteList(routeList);
            routeVo.setTotal(routeList.size());
        }
        return routeVo;
    }
    @GetMapping("/resetOrder")
    public void resetOrder(){
        service.resetOrder();
    }

    @PostMapping("/add")
    public Result add(@RequestBody Route route){
        Result result = new Result(new Object(),new Info());
        String name = route.getName();
        QueryWrapper<Route> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        List<Route> list = service.list(wrapper);
//        ID为空 说明是新增
        if (route.getId()==null){
//            重名，报错
            if (list.size()!=0){
                result.data=null;
                result.info.setCode(400);
                result.info.setMsg("景区名已存在！");
                return result;
            }
            //设置创建时间
            route.setCreateTime(new Date());
            service.add(route);
            result.data = null;
            result.info.setCode(200);
            result.info.setMsg("新增景区成功！");
            return result;
        }
//        ID不为空就是修改，修改不检查重名！！！！！！！
        else {
            service.edit(route);
            result.data = null;
            result.info.setCode(200);
            result.info.setMsg("编辑景区成功！");
        }
        return result;
    }

    @GetMapping("/getRoute")
    public Route get(@RequestParam String id,@RequestParam boolean img){
        Route route = service.getById(id);
        if (img)
            service.setImgs(route,id);
        return route;
    }

    @DeleteMapping("/del")
    public boolean delRoute(@RequestParam String id){
        service.delDetails(id);
        return service.removeById(id);
    }

    @GetMapping("/getSticky")
    public StickyRouteVo getSticky(@RequestParam Integer pageNum, @RequestParam Integer pageSize,
                                   @RequestParam String condition){
        StickyRouteVo routeStickyVo = new StickyRouteVo();
        List<StickyRoute> stickyList;

        if (condition.isEmpty()) {
            stickyList = service.getSticky((pageNum - 1) * pageSize, pageSize);
        }
        else {
            stickyList = service.search((pageNum - 1) * pageSize, pageSize,condition);
        }
        int total = service.count();
        routeStickyVo.setStickyList(stickyList);
        routeStickyVo.setTotal(total);
        return routeStickyVo;
    }

    @Autowired
    IRouteImageService imageService;
    @Autowired
    ISliderService sliderService;

    @PostMapping("/change")
    public Result change(@RequestBody StickyRoute stickyRoute){

        Result result = new Result(new Object(), new Info());

        //更新名片
        UpdateWrapper<RouteImage> routeUW= new UpdateWrapper<>();
        routeUW.eq("id", stickyRoute.getImgId());

        RouteImage routeImage = imageService.getById(stickyRoute.getImgId());
        routeImage.setTop(stickyRoute.getStickyTop());
        routeImage.setOrderNum(stickyRoute.getStickyOrder());
        routeImage.setUrl(stickyRoute.getUrl());
        imageService.update(routeImage,routeUW);

        //更新首页轮播图
        UpdateWrapper<Slider> sliderUW = new UpdateWrapper<>();
        sliderUW.eq("id",stickyRoute.getSliderId());

        Slider slider = sliderService.getById(stickyRoute.getSliderId());
        slider.setTop(stickyRoute.getSliderTop());
        slider.setOrderNum(stickyRoute.getSliderOrder());
        slider.setUrl(stickyRoute.getUrl());
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

package com.zh.mini.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.mini.entity.Route;
import com.zh.mini.entity.RouteImage;
import com.zh.mini.entity.Route;
import com.zh.mini.service.IRouteImageService;
import com.zh.mini.service.IRouteService;
import com.zh.mini.service.IRouteService;
import com.zh.mini.vo.RouteVo;
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
//        不重名 ID为空 说明是新增
        if (list.size()==0 && route.getId()==null){
            service.add(route);
            result.data = null;
            result.info.setCode(200);
            result.info.setMsg("新增景区成功！");
            return result;
        }
//        重名 ID不空 说明修改
        if (list.size()==1 && !route.getId().isEmpty()){
            service.edit(route);
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
}

package com.zh.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.admin.entity.Search;
import com.zh.admin.service.ISearchService;
import com.zh.admin.service.IVipCardService;
import com.zh.mini.bo.SearchResult;
import com.zh.mini.service.IActivityService;
import com.zh.mini.service.IRouteService;
import com.zh.mini.service.ISceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import util.Info;
import util.Result;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/mini/search")
public class SearchController {
    @Autowired
    ISceneService sceneService;
    @Autowired
    IRouteService routeService;
    @Autowired
    IActivityService activityService;
    @Autowired
    IVipCardService vipCardService;
    @Autowired
    ISearchService service;

    //godzSearch之全文检索
    @RequestMapping("/overall")
    public List<SearchResult> overall(@RequestParam String openid,@RequestParam String condition){
        //记录用户的最近6次搜索，在小程序搜索页展示
        QueryWrapper<Search> query = new QueryWrapper<>();
        query.eq("searcher",openid);
        int count = service.count(query);
        //没满6个，记录
        if (count<6){
            service.addRecentSearch(openid,condition);
        }
        //满6个，删除最早的一个，再记录
        else if (count==6){
            service.delEarliest(openid);
            service.addRecentSearch(openid, condition);
        }

        //3表模糊查询，拼接结果集
        List<SearchResult> scenes = sceneService.search(condition);
        List<SearchResult> routes = routeService.search(condition);
        List<SearchResult> activities = activityService.search(condition);

        scenes.addAll(routes);
        scenes.addAll(activities);
        return scenes;
    }
    //获取搜索记录
    @RequestMapping("/getRecent")
    public List<Search> getRecent(@RequestParam String openid){
        QueryWrapper<Search> query = new QueryWrapper<>();
        query.eq("searcher",openid);
        return service.list(query);
    }

                                //热搜业务
    //获取热搜
    @RequestMapping("/getHot")
    public List<Search> getHot() {
        QueryWrapper<Search> query = new QueryWrapper<>();
        query.eq("hot", true);
        return service.list(query);
    }
    //修改热搜
    @RequestMapping("/change")
    public Result change(@RequestBody Search search) {
        Result result = new Result(new Object(), new Info());
        service.updateById(search);
        //热搜有没有上限，有几个上限，都要由中国人民自己来决定
        result.data=null;
        result.info.setCode(200);
        return result;
    }
    //增
    @RequestMapping("/addHot")
    public void addHot(@RequestBody Search search){
        service.save(search);
    }
    //删
    @DeleteMapping("/delHot")
    public void delHot(@RequestParam String id){
        service.removeById(id);
    }
}

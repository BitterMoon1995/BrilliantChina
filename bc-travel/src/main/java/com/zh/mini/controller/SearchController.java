package com.zh.mini.controller;

import com.zh.mini.bo.SearchResult;
import com.zh.mini.service.IActivityService;
import com.zh.mini.service.IRouteService;
import com.zh.mini.service.ISceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/overall")
    public List<SearchResult> overall(@RequestParam String condition){
        List<SearchResult> scenes = sceneService.search(condition);
        List<SearchResult> routes = routeService.search(condition);
        List<SearchResult> activities = activityService.search(condition);

        scenes.addAll(routes);
        scenes.addAll(activities);
        System.out.println(scenes.toString());
        return scenes;
    }
}

package starry.evenstar.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import starry.evenstar.entity.Menu;
import starry.evenstar.service.IMenuService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 暮星公主周薇儿
 * @since 2020-05-04
 */
@CrossOrigin
@RestController
@RequestMapping("/evenstar/menu")
public class MenuController {
    @Autowired
    IMenuService service;
    @RequestMapping("/getAll")
    public List<Menu> getAll(){
        ArrayList<Menu> menus = new ArrayList<>();
        QueryWrapper<Menu> wrapper1 = new QueryWrapper<>();
        //找到所有一级菜单，加入list1
        wrapper1.eq("sid",-1);
        List<Menu> list1 = service.list(wrapper1);

        for (Menu menu : list1) {
            Integer id = menu.getId();
            QueryWrapper<Menu> wrapper2 = new QueryWrapper<>();
            //遍历list1，找到所有sid为当前menu的menu，也就是当前一级菜单的二级菜单
            wrapper2.eq("sid",id);
            List<Menu> list2 = service.list(wrapper2);
            //加入当前menu的children数组中
            menu.setChildren(list2);
        }
        return list1;

    }
}

package com.zh.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.admin.entity.Menu;
import com.zh.admin.entity.User;
import com.zh.admin.service.IMenuService;
import com.zh.admin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @Autowired
    IUserService userService;
    @RequestMapping("/getAll")
    public List<Menu> getAll(@RequestParam String username){
        QueryWrapper<User> userQ = new QueryWrapper<>();
        userQ.eq("username",username);
        User user = userService.getOne(userQ);
        //获取当前用户权限值
        Integer role = user.getRole();

        QueryWrapper<Menu> wrapper1 = new QueryWrapper<>();
        //找到所有权限和当前用户相匹配的一级菜单，加入list1。
        //具体来说是权限值大于等于当前用户的权限，比如用户role为1，那么可以得到所有1.2.3的菜单；
        //若为3，则只能得到3的菜单
        wrapper1.eq("sid",-1).ge("role",role);
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

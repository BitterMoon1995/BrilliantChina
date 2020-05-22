package com.zh.mini.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.mini.entity.Icon;
import com.zh.mini.service.IIconService;
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
 * @since 2020-05-21
 */
@RestController
@RequestMapping("/mini/icon")
public class IconController {

    @Autowired
    IIconService service;
    @PostMapping("/addCategory")
    public Result addCategory(@RequestBody List<Icon> iconList){
        Result result = new Result(new Object(), new Info());

        QueryWrapper<Icon> wrapper = new QueryWrapper<>();
        wrapper.eq("type","category");
        service.remove(wrapper);

        for (int i = 0; i < iconList.size(); i++) {
            Icon icon=iconList.get(i);
            icon.setOrderNum(i);
            service.save(icon);
        }

        result.data=null;
        result.info.setCode(200);
        return result;
    }

    @GetMapping("/getCategory")
    public List<Icon> getCategories(){
        QueryWrapper<Icon> wrapper = new QueryWrapper<>();
        wrapper.eq("type","category");
        return service.list(wrapper);
    }
}

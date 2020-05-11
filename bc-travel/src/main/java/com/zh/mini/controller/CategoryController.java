package com.zh.mini.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.mini.entity.Category;
import com.zh.mini.entity.Swiper;
import com.zh.mini.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 海琴烟
 * @since 2020-04-07
 */
@RestController
@RequestMapping("/mini/category")
public class CategoryController {
    @Autowired
    ICategoryService service;
    @RequestMapping("/addInBulk")
    public void addInBulk(@RequestBody List<Category> categories){
        int count = service.count();
        if (categories.size()==5){
            QueryWrapper<Category> wrapper = new QueryWrapper<>();
            service.remove(wrapper);
            service.saveBatch(categories);
        }
    }
    @RequestMapping("/list")
    public List<Category> list(){
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("order_num");
        return service.list(wrapper);
    }
}

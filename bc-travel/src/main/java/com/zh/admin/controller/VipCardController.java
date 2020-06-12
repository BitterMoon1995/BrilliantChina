package com.zh.admin.controller;


import com.zh.admin.service.IVipCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author God周周神
 * @since 2020-06-12
 */
@RestController
@RequestMapping("/admin/vip-card")
public class VipCardController {
    @Autowired
    IVipCardService service;


    public void saveVipInfo(){

    }
}

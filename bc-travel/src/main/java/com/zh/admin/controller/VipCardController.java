package com.zh.admin.controller;


import com.zh.admin.entity.VipCard;
import com.zh.admin.service.IVipCardService;
import com.zh.admin.utils.GodzSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/vip/vip-card")
public class VipCardController {
    @Autowired
    IVipCardService service;

    @PostMapping("/save")
    public void saveVipInfo(@RequestBody VipCard vipCard){
        //新会员注册
        if (vipCard.getId()==null){
            String name = vipCard.getRealName();
            String firstPinYin = GodzSUtils.getFirstPinYin(name);

            String randomS = GodzSUtils.getCharAndNum(6-firstPinYin.length());

            vipCard.setPromoCode(firstPinYin+randomS);
            service.save(vipCard);
        }
    }

}
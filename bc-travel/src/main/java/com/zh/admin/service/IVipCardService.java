package com.zh.admin.service;

import com.zh.admin.entity.VipCard;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author God周周神
 * @since 2020-06-12
 */
public interface IVipCardService extends IService<VipCard> {
    Integer getRemainingDays(Date expDate);

    VipCard getByOpenid(String openid);

    short checkVipStatus(String openid);
}

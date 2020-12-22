package com.zh.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.admin.entity.VipCard;
import com.zh.admin.mapper.VipCardMapper;
import com.zh.admin.service.IVipCardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.common.VipStatus;
import com.zh.core.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author God周周神
 * @since 2020-06-12
 */
@Service
public class VipCardServiceImpl extends ServiceImpl<VipCardMapper, VipCard> implements IVipCardService {

    @Override
    public Integer getRemainingDays(Date expDate) {
        long expDateTime = expDate.getTime();

        long currentDateTime = new Date().getTime();

        return Math.toIntExact((expDateTime - currentDateTime) / 86400000);
    }

    @Override
    public VipCard getByOpenid(String openid) {
        QueryWrapper<VipCard> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        return getOne(wrapper);
    }

    @Override
    public short checkVipStatus(String openid) {
        VipCard vip = getByOpenid(openid);
        if (vip==null)
        return VipStatus.notRegister;

        Date expirationTime = vip.getExpirationTime();
        return DateUtils.checkExpire(expirationTime);
    }

}

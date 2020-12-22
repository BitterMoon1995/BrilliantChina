package com.zh.core.schedule;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zh.admin.entity.VipCard;
import com.zh.admin.service.IVipCardService;
import com.zh.common.Status;
import com.zh.common.iResult;
import com.zh.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

@Configuration
@EnableScheduling
public class updateVIP {
    @Autowired
    IVipCardService vipService;

    @Scheduled(cron = "0 0 5 * * ?")
    //定时更新会员的充值状态
    public void vipState() {
        UpdateWrapper<VipCard> upd = new UpdateWrapper<>();
        try {
            List<VipCard> vips = vipService.list();

            vips.parallelStream().forEach(vipCard -> {
                Date expDate = vipCard.getExpirationTime();
                short stCode = DateUtils.checkExpire(expDate);
                upd.set("vip_status",stCode);
                vipService.update(vipCard,upd);
            });
        } catch (Exception e) {
            System.out.println("会员充值状态更新失败" + e.toString());
            return;
        }
        System.out.println("会员充值状态更新完毕" + new Date());
    }
}

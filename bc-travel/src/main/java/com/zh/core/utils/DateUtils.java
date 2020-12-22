package com.zh.core.utils;

import com.zh.common.VipStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    //检查会员过期时间
    public static short checkExpire(Date date){
        Calendar expTime = Calendar.getInstance();
        expTime.setTime(date);
        Calendar now = Calendar.getInstance();

        if (expTime.get(Calendar.YEAR)==1989)
            return VipStatus.neverCharge;

        if (expTime.before(now))
            return VipStatus.expired;

        return VipStatus.daddy;
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse("2020-11-6");
        short i = checkExpire(date);
        System.out.println(i);
    }

}

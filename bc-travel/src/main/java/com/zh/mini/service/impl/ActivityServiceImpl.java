package com.zh.mini.service.impl;

import com.zh.mini.entity.Activity;
import com.zh.mini.mapper.ActivityMapper;
import com.zh.mini.service.IActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 海琴烟
 * @since 2020-03-31
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements IActivityService {
    @Autowired
    ActivityMapper activityMapper;

    @Override
    public void addAct(Activity activity) {
        activityMapper.insert(activity);
    }

    @Override
    public Activity getAct(String id) {
        return activityMapper.selectById(id);
    }
}

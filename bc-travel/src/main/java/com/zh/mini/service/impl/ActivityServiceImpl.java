package com.zh.mini.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.mini.entity.Activity;
import com.zh.mini.entity.Activity;
import com.zh.mini.entity.ActivityImage;
import com.zh.mini.entity.Swiper;
import com.zh.mini.mapper.ActivityMapper;
import com.zh.mini.mapper.ActivityMapper;
import com.zh.mini.service.IActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.mini.service.IActivityImageService;
import com.zh.mini.service.ISwiperService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

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
    ActivityMapper mapper;
    @Autowired
    IActivityImageService imageService;
    @Autowired
    ISwiperService swiperService;
    @Override
    public void saveActivity(Activity activity) {
        boolean isBlank = StringUtils.isBlank(activity.getId());
        if (isBlank){
            boolean saved = save(activity);
            String id = "";
            if (saved) {
                id = activity.getId();
                saveDetails(activity, id);
            }
        }
        else {
            updateById(activity);

            QueryWrapper<ActivityImage> imageQueryWrapper = new QueryWrapper<>();
            imageQueryWrapper.eq("activity_id", activity.getId());
            imageService.remove(imageQueryWrapper);

            QueryWrapper<Swiper> swiperQueryWrapper = new QueryWrapper<>();
            swiperQueryWrapper.eq("target_id", activity.getId());
            swiperService.remove(swiperQueryWrapper);

            saveDetails(activity, activity.getId());
        }

    }

    @Override
    public void resetOrder() {
        List<Activity> list = this.list();
        Comparator<Object> comparator = Collator.getInstance(Locale.CHINA);
        Collections.sort(list,comparator);

        System.out.printf(String.valueOf(list));

    }

    @Override
    public void setImgs(Activity activity, String id) {
        QueryWrapper<ActivityImage> qIntros = new QueryWrapper<>();
        qIntros.eq("activity_id",id).eq("type","intros");
        List<ActivityImage> intros = imageService.list(qIntros);
        activity.setIntroImgs(intros);

        QueryWrapper<ActivityImage> qPostcard = new QueryWrapper<>();
        qPostcard.eq("activity_id",id).eq("type","postcard");
        ActivityImage postcard = imageService.getOne(qPostcard);
        activity.setPostcard(postcard);

        QueryWrapper<Swiper> qSwiper = new QueryWrapper<>();
        qSwiper.eq("target_id",id);
        Swiper swiper = swiperService.getOne(qSwiper);
        activity.setSwiper(swiper);
    }

    @Override
    public void editActivity(Activity activity) {
        mapper.updateById(activity);

        saveDetails(activity,activity.getId());
    }

    void saveDetails(Activity activity, String id){
        List<ActivityImage> introImgs = activity.getIntroImgs();
        ActivityImage postcard = activity.getPostcard();
        Swiper swiper = activity.getSwiper();
        ActivityImage richText = activity.getRichText();

        if (introImgs!=null) {
            QueryWrapper<ActivityImage> wrapper = new QueryWrapper<>();
            wrapper.eq("type","intros").eq("activity_id",id);
            imageService.remove(wrapper);

            int size = introImgs.size();
            for (int i = 0; i < size; i++) {
                ActivityImage image = introImgs.get(i);
                image.setActivityId(id);
                image.setOrderNum(i);
                imageService.save(image);
            }
        }

        if (postcard!=null) {
            QueryWrapper<ActivityImage> wrapper = new QueryWrapper<>();
            wrapper.eq("type","postcard").eq("activity_id",id);
            imageService.remove(wrapper);
            postcard.setActivityId(id);
            imageService.save(postcard);
        }

        if (swiper!=null) {
            QueryWrapper<Swiper> wrapper = new QueryWrapper<>();
            wrapper.eq("target_id",id);
            swiper.setTargetId(id);
            swiperService.save(swiper);
        }

        if (richText!=null) {
            QueryWrapper<ActivityImage> wrapper = new QueryWrapper<>();
            wrapper.eq("type","richText").eq("activity_id",id);
            imageService.remove(wrapper);
            richText.setActivityId(id);
            imageService.save(richText);
        }

    }

    public void delDetails(String id){
        QueryWrapper<ActivityImage> activityImageWrapper = new QueryWrapper<>();
        activityImageWrapper.eq("activity_id",id);
        imageService.remove(activityImageWrapper);

        QueryWrapper<Swiper> swiperWrapper = new QueryWrapper<>();
        swiperWrapper.eq("target_id",id);
        swiperService.remove(swiperWrapper);
    }

    @Override
    public List<Activity> search(String username, String name) {
        return mapper.query(username,name);
    }

    @Override
    public List<Activity> allSearch(String name) {
        return mapper.allSearch(name);
    }
}

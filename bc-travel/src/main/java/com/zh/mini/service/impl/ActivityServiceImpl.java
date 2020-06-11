package com.zh.mini.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.mini.bo.SearchResult;
import com.zh.mini.bo.StickyObject;
import com.zh.mini.entity.Activity;
import com.zh.mini.entity.ActivityImage;
import com.zh.mini.entity.Slider;
import com.zh.mini.mapper.ActivityMapper;
import com.zh.mini.service.IActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.mini.service.IActivityImageService;
import com.zh.mini.service.ISliderService;
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
    ISliderService sliderService;
    @Override
    public void add(Activity activity) {
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
            saveDetails(activity, activity.getId());
        }

    }

    @Override
    public void resetOrder() {
        mapper.resetOrder();
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

        QueryWrapper<Slider> qSlider = new QueryWrapper<>();
        qSlider.eq("target_id",id);
        Slider slider = sliderService.getOne(qSlider);
        activity.setSlider(slider);
    }

    @Override
    public void edit(Activity activity) {
        mapper.updateById(activity);

        saveDetails(activity,activity.getId());
    }

    void saveDetails(Activity activity, String id){
        List<ActivityImage> introImgs = activity.getIntroImgs();
        ActivityImage postcard = activity.getPostcard();
        Slider slider = activity.getSlider();
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

        if (slider!=null) {
            QueryWrapper<Slider> wrapper = new QueryWrapper<>();
            wrapper.eq("target_id",id);
            sliderService.remove(wrapper);
            slider.setTargetId(id);
            sliderService.save(slider);
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

        QueryWrapper<Slider> sliderWrapper = new QueryWrapper<>();
        sliderWrapper.eq("target_id",id);
        sliderService.remove(sliderWrapper);
    }

    @Override
    public List<Activity> search(String username, String name) {
        return mapper.query(username,name);
    }

    @Override
    public List<Activity> allSearch(String name) {
        return mapper.allSearch(name);
    }

    @Override
    public List<StickyObject> getSticky(Integer index, Integer offset) {
        return mapper.getSticky(index,offset);
    }

    @Override
    public List<StickyObject> search(Integer index, Integer offset, String name) {
        return mapper.search(index,offset,name);
    }

    @Override
    public List<SearchResult> search(String s) {
        return mapper.search(s);
    }


}
